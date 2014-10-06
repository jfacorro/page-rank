(ns page-rank.core
  (:refer-clojure :exclude [* - + == /])
  (:use clojure.core.matrix)
  (:use clojure.core.matrix.operators))

(def max-iterations 5)
(def sqrt-2 (Math/sqrt 2))

(defn- sum-1-beta [beta r]
  (map 
    (partial + (/ (- 1 beta) 
                 (count r)))
    r))

(defn normalise-prob [r]
  (->> r
    normalise
    (map #(/ % sqrt-2))))

(defn iteration
  [r M beta]
  (->> (mmul r M) 
    (* beta)
    normalise-prob
    (sum-1-beta beta)))

(defn power-iteration
  [r M beta epsilon]
  (loop [i 0
         r r]
    (let [r-new (iteration r M beta)
          diff  (distance r r-new)]
      (prn i diff)
      (if (or (< max-iterations i) (< diff epsilon))
        r-new
        (recur (inc i) r-new)))))

(comment
  ;; 1
  (def M [[0 1 1/3]
          [0 0 1/3]
          [0 0 1/3]])
  (def r [1 1 1])
  (let [result  (power-iteration r M 0.7 0.0000001)
        [a b c] (->> result
                  normalise-prob
                  (map (partial * 3)))]
    (println (reduce + [a b c]))
    (println :a+c (+ a c))
    (println :a+b (+ a b))
    (println :b+c (+ b c)))
)

  ;; 3
  (def M [[0 1 1/2]
          [0 0 1/2]
          [1 0 0]])
  (def r [1 1 1])
  (let [result  (power-iteration r M 1 0.000000001)]
    (println result))
