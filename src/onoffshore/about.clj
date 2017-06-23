(ns onoffshore.about
  (:require [hiccup.page :refer [html5 include-css]]
            [onoffshore.layout :refer [with-navbar]]))

(defn render [{global-meta :meta posts :entries :as opts}]
  (with-navbar opts 
    [:p "The attentive worker is diligent, humble, painstaking and relentless."]
    [:img {:src "images/attentive-worker-1913.jpg"}]))

