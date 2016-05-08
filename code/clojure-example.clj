(def hickey {:firstname "Rich"
             :lastname "Hickey"})
(def odersky {:firstname "Martin"
              :lastname "Odersky"})
(def wadler {:firstname "Philip"
             :lastname "Wadler"})

(def persons [hickey,odersky,wadler])

(def mapped (map :firstname persons))
(def filtered (filter #(> (count %) 4) mapped))
(def reduced (reduce (fn [acc v] (+ acc (count v))) 0 filtered))

(defn count-total-length-of-firstnames-longer-than-four-chars [persons]
  (let [firstnames (map :firstname persons)
        longer-than-4 (filter #(> (count %) 4) mapped)
        total-length (reduce (fn [acc v] (+ acc (count v))) 0 filtered)]
    total-length))

;; lein repl
;; (load-file "clojure-example.clj")
