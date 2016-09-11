(ns inkstain.core-test
  (:require [clojure.test :refer :all]
            [inkstain.core :refer :all]
            [clojure.xml :as xml]
            [clojure.zip :as zip]))

(defn zip-str [s]
  (zip/xml-zip  (xml/parse (java.io.ByteArrayInputStream. (.getBytes s)))))

(deftest test-xml
  (testing "parse-serialize equality"
    (def input "<?xml version='1.0' encoding='UTF-8'?><xml/>")
    (def input2 "<?xml version='1.0' encoding='UTF-8'?><xml><foo attr='true'>bar</foo></xml>")

    (is (= input (serialize (first (zip-str input)))))
    (is (= input2 (serialize (first (zip-str input2)))))
    ))
