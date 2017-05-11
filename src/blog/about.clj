(ns blog.about
  (:require [hiccup.page :refer [html5 include-css]]
            [blog.layout :refer [with-navbar]]))

(defn render [{global-meta :meta posts :entries :as opts}]
  (with-navbar opts 
    [:p "This is a demo of a static page, for content that won't change."]))

