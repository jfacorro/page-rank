(ns page-rank.assignment
  (:require [page-rank.core :as pgr]
            [clojure.string :as str]))

(set! *warn-on-reflection* true)

(defn register-node [acc node]
  (if (get-in acc [:nodes node])
    acc
    (as-> (inc (:count acc)) new-count
      (-> acc
        (assoc! :nodes (assoc! (:nodes acc) node new-count))
        (assoc! :count new-count)))))

(defn process-edge 
  [acc edges]
  (let [numbers  (.split ^String edges "\t")
        origin   (-> numbers (aget 0) Integer/parseInt)
        target   (-> numbers (aget 1) Integer/parseInt)]
    (-> acc
      (assoc! :edges (assoc! (:edges acc) origin target))
      (register-node origin)
      (register-node target))))

(defn load-matrix [file]
  (with-open [input (clojure.java.io/reader file)]
    (let [lines (line-seq input)
          info  (transient
                  {:nodes (transient {})
                   :edges (transient {})
                   :count 0})]
      (->> lines
        #_(take 2000000)
        (reduce process-edge info)
        persistent!))))

(time
  (def info (load-matrix "web-Google.txt")))

(prn (:count info) (count (:nodes info)))
