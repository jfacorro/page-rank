(ns page-rank.core
  (:refer-clojure :exclude [* - + == /])
  (:use clojure.core.matrix
        clojure.core.matrix.operators))

(def max-iterations 1000)

(defn- sum-1-beta [beta r]
  (let [delta  (/ (- 1 beta) (count r))]
    (map (partial + delta) r)))

(defn normalise-prob [r]
  (let [delta (/ (- 1 (reduce + r))
                 (count r))]
    (map (partial + delta) r)))

(defn iteration
  [r M beta]
  (->> (mmul M r)
    (* beta)
    (sum-1-beta beta)
    #_normalise-prob))

(defn power-iteration
  [r M beta epsilon & [n]]
  (loop [i 0
         r-old r]
    (let [r-new (iteration r-old M beta)
          diff  (distance r-old r-new)]
      (if (or (<= (or n max-iterations) (inc i))
              (< diff epsilon))
        r-new
        (recur (inc i) r-new)))))
