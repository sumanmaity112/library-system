(ns library.system.service.book-service-test
  (:require [clojure.test :refer :all]
            [library.system.service.book-service :refer [add-new-book]]
            [library.system.utils.utils :as utils])
  )

(defn create-sandbox [predicate] (utils/create-book-info-table) (predicate) (utils/drop-book-info-table))

(use-fixtures :once create-sandbox)

(deftest should-add-a-new-book
  (let [result (add-new-book "service-test" 9)]
    (are [expected actual] (= expected actual)
                           "service-test" (result :name)
                           false (nil? (get result :id)))
    ))