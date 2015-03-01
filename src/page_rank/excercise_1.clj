(ns page-rank.excercise-1
  (:require [page-rank.core :as pgr]))

(let [beta    0.7
      epsilon 0.0000001
      M       [[0 0 0]
               [1/2 0 0]
               [1/2 1 1]]
      r       [1/3 1/3 1/3]
      N       (vec (repeat 3 r))
      result  (pgr/power-iteration r M beta epsilon)
      [a b c] (->> result (map (partial * 3)))]
  (println (+ a b c))
  (println :a+c (+ a c))
  (println :a+b (+ a b))
  (println :b+c (+ b c)))
