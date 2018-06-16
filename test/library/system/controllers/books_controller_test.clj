(ns library.system.controllers.books-controller-test
  (:require [clojure.test :refer :all])
  (:require [library.system.controllers.books-controller :refer [app]]
            [ring.mock.request :as mock]
            [library.system.service.book-service :as book-service]))

(deftest should-add-new-book-to-system
  (with-redefs [book-service/add-new-book (fn [^String name ^Integer no-of-copies] {:name name :id 1})]
    (let [response (app (-> (mock/request :post "/add") (mock/body {:name "test" :noOfCopies "6"})))]
      (are [expected actual] (= expected actual)
                             200 (response :status)
                             {"Content-Type" "application/json; charset=utf-8"} (response :headers)
                             "{\"name\":\"test\",\"id\":1}" (response :body)
                             )
      )
    ))