(ns page-rank.excercise-w7b-1
  (:require [page-rank.core :as pgr]
            [clojure.core.matrix :as mtx]
            [clojure.core.matrix.operators :as mtx-ops]))

(let [beta    0.7
      epsilon 0.0000000001
      M       [[0   1  0  0]
               [1/2 0  0  0]
               [1/2 0  0  1]
               [0   0  1  0]]
      S       #{1 2}
      beta-ts (/ (- 1 beta) 3)
      TS      [(repeat 4 (* 2 beta-ts))
               (repeat 4 (* 1 beta-ts))
               [0 0 0 0]
               [0 0 0 0]]
      r       [1/4 1/4 1/4 1/4]
      A       (mtx-ops/+ (mtx-ops/* beta M) TS)
      result  (pgr/power-iteration r A epsilon)]
   (prn result))
