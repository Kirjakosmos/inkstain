(ns inkstain.core)

(defn serializeAttr [attr]
  (str " " (name (key attr)) "='" (val attr)"'"))

(defn serializeElement [e]
  (if (instance? String e)
    e
    (str (str "<" (name (:tag e)))
      (when (:attrs e)
        (clojure.string/join (map serializeAttr (:attrs e))))
      (if (:content e)
        (str ">"
          (clojure.string/join (map serializeElement (:content e)))
          (str "</" (name (:tag e)) ">"))
        "/>"))))

(defn serialize [x]
  (str "<?xml version='1.0' encoding='UTF-8'?>" (serializeElement x)))
