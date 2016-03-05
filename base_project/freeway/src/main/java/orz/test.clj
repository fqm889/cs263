(defn func [x] (+ x 1))

(func 3)
(loop [a -3] (if (> a 0) (+ a 10) (recur (func a))))


