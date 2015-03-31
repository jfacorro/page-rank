(ns page-rank.excercise-w7a-3
  (:require [page-rank.core :as pgr]
            [clojure.core.matrix :as mtx]
            [clojure.core.matrix.operators :as mtx-ops]))

(def L [[0 1 0 0]
        [1 0 0 0]
        [1 0 0 1]
        [0 0 1 0]])

(def Lt (mtx/transpose L))

(def h  [1 1 1 1])

(def a (mtx/mmul L h))
(def a (mtx-ops// a (apply max a)))

(def h (mtx/mmul Lt a))
(def h (mtx-ops// h (apply max h)))

(def a (mtx/mmul L h))
(def a (mtx-ops// a (apply max a)))

(def h (mtx/mmul Lt a))
(def h (mtx-ops// h (apply max h)))

(prn :a a :h h)
