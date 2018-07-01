{:profiles/test {:env {:database-name     "library_test",
                       :host-name         "192.168.33.10"
                       :username          "library_admin"
                       :password          ""
                       :dbtype            "postgres"
                       :driver-class-name "org.postgresql.Driver"}},
 :profiles/dev  {:env {:database-name     "library",
                       :host-name         "192.168.33.10"
                       :username          "library_admin"
                       :password          ""
                       :dbtype            "postgres"
                       :driver-class-name "org.postgresql.Driver"}}
 }