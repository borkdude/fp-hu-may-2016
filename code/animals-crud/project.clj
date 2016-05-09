(defproject animals-crud "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [org.clojure/core.async "0.2.374"]
                 [ring-server "0.4.0"]
                 [compojure "1.5.0"]
                 [org.webjars/bootstrap "3.3.6"]
                 [cljs-http "0.1.40"]
                 [liberator "0.14.1"]
                 [fogus/ring-edn "0.3.0"]
                 [clj-json "0.5.3"]
                 [hiccup "1.0.5"]
                 [reagent "0.5.1"]
                 [prismatic/schema "1.1.1"]
                 [org.clojure/java.jdbc "0.5.8"]
                 [com.h2database/h2 "1.4.191"]]

  :plugins [[lein-cljsbuild "1.1.3"]]

  :clean-targets ^{:protect false} [:target-path :compile-path "resources/public/out"]

  :source-paths ["src"]

  :profiles {:dev {:plugins [[lein-figwheel "0.5.3-1"]]
                   :figwheel {:http-server-root "public"
                              :port 3449}}}

  :cljsbuild {:builds [{:id "reagent"
                        :source-paths ["src-cljs/"]
                        :figwheel true
                        :compiler {:output-to "resources/public/crud.js"
                                   :output-dir "resources/public/out"
                                   :optimizations :none
                                   :asset-path "out"
                                   :main "animals.crud"
                                   :source-map true}}]}

  :repl-options {:init-ns animals.repl}
  
  :global-vars {*print-length* 20})
