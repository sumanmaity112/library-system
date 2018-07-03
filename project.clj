(defproject library.system "0.1.0-SNAPSHOT"
  :description "A Library System fully written in Clojure"
  :url "https://github.com/sumanmaity112/library-system"
  :license
  {:name "Eclipse Public License"
   :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-jetty-adapter "1.7.0-RC1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [compojure "1.6.1"]
                 [clj-time "0.14.2"]
                 [org.clojure/java.jdbc "0.7.6"]
                 [org.postgresql/postgresql "42.2.2"]
                 [environ "1.1.0"]
                 [ragtime "0.7.2"]
                 [selmer "1.11.8"]]
  :plugins [[lein-environ "1.1.0"]]

  :profiles {
             :uberjar      {:omit-source true
                            :aot         :all
                            }
             :dev          [:project/dev :profiles/dev]
             :test         [:dev :project/test :profiles/test]
             :project/dev  {
                            :dependencies [[ring/ring-mock "0.3.2"]
                                           [pjstadig/humane-test-output "0.8.3"]]
                            :plugins      [[com.jakemccrary/lein-test-refresh "0.22.0"]
                                           [lein-kibit "0.1.6"]
                                           [lein-pprint "1.1.1"]]
                            :injections   [(require 'pjstadig.humane-test-output)
                                           (pjstadig.humane-test-output/activate!)]
                            :test-refresh {:notify-command ["terminal-notifier" "-title" "Tests" "-message"]
                                           :quiet          true
                                           :changes-only   true}
                            }
             :project/test {}
             }
  :main ^:skip-aot library.system.application)
