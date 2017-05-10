(ns blog.about
  (:require [hiccup.page :refer [html5 include-css]]))


(defn render [{global-meta :meta posts :entries}]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
    [:head
      [:title (:site-title global-meta)]
      [:meta {:charset "utf-8"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]]
      (include-css "css/tomlynch.css")
    [:body
     [:p "This is a demonstration of a static page, for content that won't change"]]))
