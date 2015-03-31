(ns page-rank.core
  (:refer-clojure :exclude [* - + == /])
  (:use clojure.core.matrix
        clojure.core.matrix.operators))

(def max-iterations 1000)

(defn build-matrix [n v]
  (repeat n (repeat n v)))

(defn- sum-1-beta [beta r]
  (let [delta  (/ (- 1 beta) (count r))]
    (map (partial + delta) r)))

(defn normalise-prob [r]
  (let [delta (/ (- 1 (reduce + r))
                 (count r))]
    (map (partial + delta) r)))

(defn power-iteration
  [r A epsilon & [n]]
  (loop [i 0
         r-old r]
    (let [r-new (->> (mmul A r-old) normalise-prob)
          diff  (distance r-old r-new)]
      (if (or (<= (or n max-iterations) (inc i))
            (< diff epsilon))
        r-new
        (recur (inc i) r-new)))))
