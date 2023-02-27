(ns p-p-p-pokerface)

(defn assert [test] (if (false? test) (throw (AssertionError. "test failed"))))

(defn suit [card]
  "Exercise 1: Write the function (suit card) which takes a singe card and
   returns the suit of the card as a one character string.
       (suit \"2H\") => \"H\"
       (suit \"2D\") => \"D\"
       (suit \"2C\") => \"C\"
       (suit \"3S\") => \"S\""
  (let [[_ s] card]
    (str s)))

(assert (= "H" (suit "2H")))
(assert (= "D" (suit "2D")))
(assert (= "C" (suit "2C")))
(assert (= "S" (suit "3S")))

(defn rank [card]
  nil)

(defn pair? [hand]
  nil)

(defn three-of-a-kind? [hand]
  nil)

(defn four-of-a-kind? [hand]
  nil)

(defn flush? [hand]
  nil)

(defn full-house? [hand]
  nil)

(defn two-pairs? [hand]
  nil)

(defn straight? [hand]
  nil)

(defn straight-flush? [hand]
  nil)

(defn value [hand]
  nil)
