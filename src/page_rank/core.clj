(ns page-rank.core
  (:refer-clojure :exclude [* - + == /])
  (:use clojure.core.matrix)
  (:use clojure.core.matrix.operators))

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
;;      (prn :iter (inc i) :new r-new)
      (if (or (<= (or n max-iterations) (inc i))
              (< diff epsilon))
        r-new
        (recur (inc i) r-new)))))

(comment
  ;; 1
  (let [beta    0.7
        epsilon 0.0000001
        M       [[0 0 0]
                 [1/2 0 0]
                 [1/2 1 1]]
        r       [1/3 1/3 1/3]
        N       (vec (repeat 3 r))
        result  (power-iteration r M beta epsilon)
        [a b c] (->> result (map (partial * 3)))]
    (println (+ a b c))
    (println :a+c (+ a c))
    (println :a+b (+ a b))
    (println :b+c (+ b c)))

  ;; 2
  (let [beta    0.85
        epsilon 0.0000001
        M       [[0   0 1]
                 [1/2 0 0]
                 [1/2 1 0]]
        r       [1 1 1]
        N       (vec (repeat 3 r))
        result  (power-iteration r M beta epsilon)
        [a b c] (->> result (map (partial * 3)))]
    (println (+ a b c))
    (println :c=b+.575a c := (+ b (* 0.575 a)))
    (println :85b=.575a+.15c (* 85 b) := (+ (* 0.15 c) (* 0.575 a))) 
    (println :.95c=.9b+.475a (* 0.95 c) := (+ (* 0.9 b) (* 0.475 a)))
    (println :.85c=b+.575a (* 0.85 c) := (+ b (* 0.575 a))))

  ;; 3
  (let [M       [[0   0 1]
                 [1/2 0 0]
                 [1/2 1 0]]
        r       [1 1 1]
        result  (power-iteration r M 1 0.000001 6)]
    (println result)
    (println (map float result)))


)