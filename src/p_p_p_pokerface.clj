(ns p-p-p-pokerface)

(defn suit [card]
  "Exercise 1: Write the function (suit card) which takes a singe card and
   returns the suit of the card as a one character string."
  (let [[_ s] card]
    (str s)))

(assert (= "H" (suit "2H")))
(assert (= "D" (suit "2D")))
(assert (= "C" (suit "2C")))
(assert (= "S" (suit "3S")))

(defn rank [card]
  "Exercise 2: Write the function (rank card) which takes a single card and
   returns the rank as a number between 2 and 14."
  (let [[r _] card
        ranks [\2 \3 \4 \5 \6 \7 \8 \9 \T \J \Q \K \A]]
    (+ 2 (.indexOf ranks r))))

(assert (= 2 (rank "2H")))
(assert (= 4 (rank "4S")))
(assert (= 10 (rank "TS")))
(assert (= 11 (rank "JS")))
(assert (= 12 (rank "QS")))
(assert (= 13 (rank "KS")))
(assert (= 14 (rank "AS")))

(def high-seven                   ["2H" "3S" "4C" "5C" "7D"])
(def pair-hand                    ["2H" "2S" "4C" "5C" "7D"])
(def two-pairs-hand               ["2H" "2S" "4C" "4D" "7D"])
(def three-of-a-kind-hand         ["2H" "2S" "2C" "4D" "7D"])
(def four-of-a-kind-hand          ["2H" "2S" "2C" "2D" "7D"])
(def straight-hand                ["2H" "3S" "6C" "5D" "4D"])
(def low-ace-straight-hand        ["2H" "3S" "4C" "5D" "AD"])
(def high-ace-straight-hand       ["TH" "AS" "QC" "KD" "JD"])
(def flush-hand                   ["2H" "4H" "5H" "9H" "7H"])
(def full-house-hand              ["2H" "5D" "2D" "2C" "5S"])
(def straight-flush-hand          ["2H" "3H" "6H" "5H" "4H"])
(def low-ace-straight-flush-hand  ["2D" "3D" "4D" "5D" "AD"])
(def high-ace-straight-flush-hand ["TS" "AS" "QS" "KS" "JS"])

(defn n-of-a-kind? [hand n]
  "Indicates if the hand contains n of a kind."
  (let [ranks (map rank hand)
        freqs (vals (frequencies ranks))]
    (>= (apply max freqs) n)))

(defn pair? [hand]
  "Exercise 3: Write the function (pair? hand) that returns true if there is a
   pair in hand and false if there is no pair in hand."
  (n-of-a-kind? hand 2))

(assert (pair? pair-hand))
(assert (pair? three-of-a-kind-hand))
(assert (not (pair? high-seven)))

(defn three-of-a-kind? [hand]
  "Exercise 4: Write the function (three-of-a-kind? hand) that returns true if
   the hand contains a three of a kind."
  (n-of-a-kind? hand 3))

(assert (not (three-of-a-kind? two-pairs-hand)))
(assert (three-of-a-kind? three-of-a-kind-hand))

(defn four-of-a-kind? [hand]
  "Exercise 5: Write the function (four-of-a-kind? hand) that returns true if
   the hand contains a four of a kind."
  (n-of-a-kind? hand 4))

(assert (not (four-of-a-kind? two-pairs-hand)))
(assert (four-of-a-kind? four-of-a-kind-hand))

(defn flush? [hand]
  "Exercise 6: Write the function (flush? hand) that returns true if the hand is
   a flush."
  (let [suits (map suit hand)
        uniq (into #{} suits)]
    (= (count uniq) 1)))

(assert (not (flush? pair-hand)))
(assert (flush? flush-hand))

(defn full-house? [hand]
  "Exercise 7: Write the function (full-house? hand) that returns true if hand
   is a full house, and otherwise false."
  (let [ranks (map rank hand)
        freqs (into #{} (vals (frequencies ranks)))]
    (= freqs #{2 3})))

(assert (not (full-house? three-of-a-kind-hand)))
(assert (full-house? full-house-hand))

(defn two-pairs? [hand]
  "Exercise 8: Write the function (two-pairs? hand) that return true if hand has
   two pairs, and otherwise false. Note that a four of a kind is also two pairs."
  (let [ranks (map rank hand)
        freqs (vals (frequencies ranks))
        pair-count (count (filter #(>= % 2) freqs))]
    (or (= pair-count 2) (four-of-a-kind? hand))))

(assert (two-pairs? two-pairs-hand))
(assert (not (two-pairs? pair-hand)))
(assert (two-pairs? four-of-a-kind-hand))

(defn straight? [hand]
  "Exercise 9: Write the function (straight? hand) that returns true if hand is
   a straight, and otherwise false. Note that an ace is accepted both as a rank
   1 and rank 14 card in straights."
  (let [ranks (map rank hand)
        uniq (into #{} ranks)
        low (apply min uniq)
        high (apply max uniq)
        std-straight? (and (= (count uniq) 5) (= (- high low) 4))
        low-ace-straight? (= uniq #{2 3 4 5 14})]
    (or std-straight? low-ace-straight?)))

(assert (not (straight? two-pairs-hand)))
(assert (straight? straight-hand))
(assert (straight? low-ace-straight-hand))
(assert (not (straight? ["2H" "2D" "3H" "4H" "5H"])))
(assert (straight? high-ace-straight-hand))

(def straight-flush?
  "Exercise 10: Write the function (straight-flush? hand) which returns true if
   the hand is a straight flush, that is both a straight and a flush, and
   otherwise false."
  (every-pred straight? flush?))

(assert (not (straight-flush? straight-hand)))
(assert (not (straight-flush? flush-hand)))
(assert (straight-flush? straight-flush-hand))
(assert (straight-flush? low-ace-straight-flush-hand))
(assert (straight-flush? high-ace-straight-flush-hand))

(defn high-card? [hand]
  "All hands have a high card."
  true)

(defn value [hand]
  "Exercise 11: Write the function (value hand), which returns the value of a hand"
  (let [checkers #{[high-card? 0]  [pair? 1]
                   [two-pairs? 2]  [three-of-a-kind? 3]
                   [straight? 4]   [flush? 5]
                   [full-house? 6] [four-of-a-kind? 7]
                   [straight-flush? 8]}
        vals (map second (filter #((first %) hand) checkers))]
    (apply max vals)))

(assert (= (value high-seven) 0))
(assert (= (value pair-hand) 1))
(assert (= (value two-pairs-hand) 2))
(assert (= (value three-of-a-kind-hand) 3))
(assert (= (value straight-hand) 4))
(assert (= (value flush-hand) 5))
(assert (= (value full-house-hand) 6))
(assert (= (value four-of-a-kind-hand) 7))
(assert (= (value straight-flush-hand) 8))
