(ns page-rank.excercise-1
  (:require [page-rank.core :as pgr]
            [clojure.core.matrix :as mtx]
            [clojure.core.matrix.operators :as mtx-ops]))

(let [beta    0.7
      epsilon 0.0000001
      M       [[0 0 0]
               [1/2 0 0]
               [1/2 1 1]]
      r       [1/3 1/3 1/3]
      A       (mtx-ops/+ (mtx-ops/* beta M) (pgr/build-matrix 3 (/ (- 1 beta) 3)))
      ;;_       (prn A)
      result  (pgr/power-iteration r A epsilon)
      [a b c] (->> result (map (partial * 3)))]
  (println (+ a b c) [a b c])
  (println :a+c (+ a c))
  (println :a+b (+ a b))
  (println :b+c (+ b c)))
