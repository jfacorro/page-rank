(ns page-rank.excercise-1
  (:require [page-rank.core :as pgr]))

;; 2
(let [beta    0.85
      epsilon 0.0000001
      M       [[0   0 1]
               [1/2 0 0]
               [1/2 1 0]]
      r       [1 1 1]
      N       (vec (repeat 3 r))
      result  (pgr/power-iteration r M beta epsilon)
      [a b c] (->> result (map (partial * 3)))]
  (println (+ a b c))
  (println :c=b+.575a c := (+ b (* 0.575 a)))
  (println :85b=.575a+.15c (* 85 b) := (+ (* 0.15 c) (* 0.575 a))) 
  (println :.95c=.9b+.475a (* 0.95 c) := (+ (* 0.9 b) (* 0.475 a)))
  (println :.85c=b+.575a (* 0.85 c) := (+ b (* 0.575 a))))
