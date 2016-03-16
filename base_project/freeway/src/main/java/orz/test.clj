(defn inc [x] (+ x 1))
(inc 3)
(def y -3)
(loop [a y] (if (>= a 0) (+ a 10) (recur (inc a))))


