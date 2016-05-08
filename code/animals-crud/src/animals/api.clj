(ns animals.api
    (:require
     [liberator.core :refer (resource)]
     [compojure.core :refer (defroutes ANY GET)]
     [compojure.route :refer (resources not-found)]
     [ring.middleware.params :refer (wrap-params)]
     [ring.middleware.edn :refer (wrap-edn-params)]
     [ring.util.response :refer (redirect)]
     [animals.animals :as animals]
     [clj-json.core :as json]
     [animals.db :as db]
     [clojure.edn :as edn]))

(defn handle-exception
  [ctx]
  (let [e (:exception ctx)]
    (.printStackTrace e)
    {:status 500 :message (.getMessage e)}))

(defroutes routes

  (ANY "/animals"
       [name species]
       (resource
        :available-media-types ["application/edn" "application/json"]
        :allowed-methods [:get :post]
        :handle-ok (fn [ctx]
                     (let [found (animals/read db/db)]
                       (condp = (-> ctx :representation :media-type)
                         "application/edn" found
                         "application/json" (json/generate-string found))))
        :post! (fn [ctx] {::id (animals/create! db/db {:name name :species species})})
        :post-redirect? (fn [ctx] {:location (str "/animals/" (::id ctx))})
        :handle-exception handle-exception))

  (ANY "/animals/:id"
       [id name species]
       (let [id (edn/read-string id)]
         (resource
           :available-media-types ["application/edn"]
           :allowed-methods [:get :put]
           :handle-ok (fn [ctx]
                        (animals/read db/db id))
           :put! (fn [ctx]
                   (animals/update!
                     db/db id
                     {:name name :species species}))
           :new? false
           :respond-with-entity? true
           :handle-exception handle-exception)))

  (GET "/greeting" []
       "Hello World!")
  (ANY "/"
       []
       (redirect "/index.html"))

  (resources "/" {:root "public"})
  (resources "/" {:root "/META-INF/resources"})
  (not-found "404"))

(def handler
  (-> routes
      wrap-params
      wrap-edn-params))

(defn init
  []
  (println "initializing application")
  (animals/init db/db))
