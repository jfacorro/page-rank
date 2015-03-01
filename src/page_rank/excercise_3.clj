(ns page-rank.excercise-1
  (:require [page-rank.core :as pgr]))

(let [M       [[0   0 1]
               [1/2 0 0]
               [1/2 1 0]]
      r       [1 1 1]
      result  (pgr/power-iteration r M 1 0.000001 6)]
  (println result)
  (println (map float result)))
