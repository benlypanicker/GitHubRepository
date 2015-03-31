/*!
 * SAP UI development toolkit for HTML5 (SAPUI5/OpenUI5)
 * (c) Copyright 2009-2015 SAP SE or an SAP affiliate company.
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */
sap.ui.define(['jquery.sap.global'],function(q){"use strict";var R={};R.render=function(j,l){var v=j;l.doBeforeRendering();v.write("<div");v.writeControlData(l);v.addClass("sapUiRoadMap");v.writeClasses();v.writeAttribute("tabIndex","0");var T=l.getTooltip_AsString();if(T){v.writeAttributeEscaped("title",T)}v.writeAttribute("style","width:"+(l.getWidth()?l.getWidth():"100%")+";");v.write(">");r(v,l,true);v.write("<ul");v.writeAttribute("id",l.getId()+"-steparea");v.addClass("sapUiRoadMapStepArea");v.writeClasses();if(sap.ui.getCore().getConfiguration().getAccessibility()){v.writeAttribute("role","group");v.writeAttributeEscaped("aria-label",k("RDMP_DEFAULT_TOOLTIP",[]));if(T){v.writeAttributeEscaped("title",T)}}v.write(">");var S=l.getSteps();for(var i=0;i<S.length;i++){var x=S[i];if(x.getSubSteps().length>0){f(v,l,x)}else{a(v,l,x)}}v.write("</ul>");r(v,l,false);v.write("</div>")};R.selectStepWithId=function(i,I){var C=i.getSelectedStep();if(C){q.sap.byId(C).removeClass("sapUiRoadMapSelected")}if(I){q.sap.byId(I).addClass("sapUiRoadMapSelected")}if(sap.ui.getCore().getConfiguration().getAccessibility()){if(C){q.sap.byId(C+"-box").removeAttr("aria-checked")}if(I){q.sap.byId(I+"-box").attr("aria-checked",true)}}};R.selectStep=function(j,S,I,l,E,v){if(!v){R.selectStepWithId(j,S.getId())}if(!I&&S.getSubSteps().length>0){var x=S.getSubSteps();var D=S.$();var y=D.hasClass("sapUiRoadMapExpanded");var C=1;var z=function(){C--;if(C>0){return}if(E){E(!y?"expanded":"collapsed")}R.updateStepArea(j)};var A=function(F,O,G){var H=q.sap.byId(F);if(!q.fx.off&&!l){H.width(O?"0px":j.iStepWidth);var L=q.sap.byId(F+"-label");L.addClass("sapUiRoadMapHidden");if(O){H.toggleClass("sapUiRoadMapHidden")}H.animate({width:O?j.iStepWidth:"0px"},"fast",function(){if(!O){H.toggleClass("sapUiRoadMapHidden")}H.width("");L.removeClass("sapUiRoadMapHidden");if(G){G()}})}else{H.toggleClass("sapUiRoadMapHidden");if(G){G()}}};D.toggleClass("sapUiRoadMapExpanded");if(sap.ui.getCore().getConfiguration().getAccessibility()){var B=D.hasClass("sapUiRoadMapExpanded");S.$("box").attr("aria-expanded",B);S.$("expandend-box").attr("aria-expanded",B)}for(var i=0;i<x.length;i++){if(x[i].getVisible()){C++;A(x[i].getId(),!y,z)}}A(S.getId()+"-expandend",!y,z)}else{if(E){E("selected")}}};R.updateStepArea=function(i){if(i.iStepWidth!=-1){var S=i.$("steparea");var j=i.$("Start");var E=i.$("End");var l=i.$();var v=S.scrollLeft();var A=l.width()-j.outerWidth(true)-E.outerWidth(true);var M=i.getNumberOfVisibleSteps();var C=m(i);if(M<1){M=C}else{M=Math.min(M,C)}var P=Math.floor(A/i.iStepWidth);var N=Math.min(M,P);S.width(N*i.iStepWidth).scrollLeft(v);u(i)}};R.updateScrollArea=function(i,S){R.updateStepArea(i);if(!S){var j=i.$("steparea");var P=s(i,false);if(i.getFirstVisibleStep()){var l=q.sap.byId(i.getFirstVisibleStep());if(l.length){P=n(j,l)}}t(i,P+o()*j.scrollLeft(),true)}};R.isVisibleRef=function(j,I){var S=j.$("steparea");var l=S.children(":visible");for(var i=0;i<l.length;i++){var C=q(l.get(i));if(C.attr("id")==I){var P=n(S,C);return P>=0&&P<S.width()}}return false};R.getFirstVisibleRef=function(j){var S=j.$("steparea");var l=S.children(":visible");for(var i=0;i<l.length;i++){var C=q(l.get(i));if(n(S,C)==0){return C}}return null};R.setStepLabel=function(S,L){var l=L?q.sap.escapeHTML(L):"";S.$("label").html(l);S.$("expandend-label").html(l);if(!sap.ui.getCore().getConfiguration().getAccessibility()){return}S.$("box").attr("aria-label",c(S,L));S.$("expandend-box").attr("aria-label",c(S,L))};R.setStepEnabled=function(i,S,E){var j=S.$();var l=S.$("expandend");if(E){j.removeClass("sapUiRoadMapDisabled");l.removeClass("sapUiRoadMapDisabled");if(sap.ui.getCore().getConfiguration().getAccessibility()){S.$("box").removeAttr("aria-disabled");S.$("expandend-box").removeAttr("aria-disabled")}return false}else{var v=i.getSelectedStep()==S.getId();if(v){j.removeClass("sapUiRoadMapSelected")}j.addClass("sapUiRoadMapDisabled");l.addClass("sapUiRoadMapDisabled");if(sap.ui.getCore().getConfiguration().getAccessibility()){var x=S.$("box");x.attr("aria-disabled",true);if(v){x.removeAttr("aria-checked")}S.$("expandend-box").attr("aria-disabled",true)}return v}};R.setStepVisible=function(j,S,I,v){var l=S.$();var x=S.$("expandend");var y=j.getSelectedStep()==S.getId();var P=S.getParent();if(I){if(P.getEnabled()&&P.getVisible()&&P.getExpanded()){if(v){l.removeClass("sapUiRoadMapHidden")}else{l.addClass("sapUiRoadMapHidden")}}}else{if(v){l.removeClass("sapUiRoadMapHidden")}else{l.addClass("sapUiRoadMapHidden")}var z=S.getSubSteps();if(z.length>0&&S.getExpanded()){if(v){x.removeClass("sapUiRoadMapHidden")}else{x.addClass("sapUiRoadMapHidden")}for(var i=0;i<z.length;i++){if(z[i].getVisible()){var A=z[i].$();if(j.getSelectedStep()==z[i].getId()){y=true;A.removeClass("sapUiRoadMapSelected");z[i].$("box").removeAttr("aria-checked")}if(v){A.removeClass("sapUiRoadMapHidden")}else{A.addClass("sapUiRoadMapHidden")}}}}}return y};R.setRoadMapWidth=function(i,W){var j=i.$();j.attr("style","width:"+(W?W:"100%")+";")};R.scrollToNextStep=function(i,D,E){var P=D;if(D=="first"||D=="last"){P=s(i,D=="last")}t(i,P,false,E)};R.addEllipses=function(S){if(!S){return}var j=S.$("label");var O=S.getLabel();var T=O+"";var C=q("<label class=\"sapUiRoadMapTitle\" style=\"display:none;position:absolute;overflow:visible;font-weight:bold;height:auto\">"+T+"</label>");C.width(j.width());q(sap.ui.getCore().getStaticAreaRef()).append(C);var i=false;while(T.length>0&&C.height()>j.height()){T=T.substr(0,T.length-1);C.html(q.sap.escapeHTML(T+"..."));i=true}if(i){j.html("<span>"+q.sap.escapeHTML(T)+"</span>");j.attr("title",S.getLabel())}else{j.attr("title",g(S))}C.remove()};R.updateStepAria=function(S){if(!sap.ui.getCore().getConfiguration().getAccessibility()){return}var I=S.getParent()instanceof sap.ui.commons.RoadMap;var j=S.getParent()[I?"getSteps":"getSubSteps"]();for(var i=0;i<j.length;i++){var P=d(j[i]);var l=e(j[i]);var v=j[i].$("box");v.attr("aria-posinset",P);v.attr("aria-setsize",l);if(I&&j[i].getSubSteps().length>0){v=j[i].$("expandend-box");v.attr("aria-posinset",P);v.attr("aria-setsize",l)}}};var r=function(i,j,S){var T=S?"Start":"End";i.write("<div");i.writeAttribute("id",j.getId()+"-"+T);i.writeAttribute("tabindex","-1");var l=true;i.addClass(l?"sapUiRoadMap"+T+"Scroll":"sapUiRoadMap"+T+"Fixed");i.addClass("sapUiRoadMapDelim");i.addClass("sapUiRoadMapContent");i.writeClasses();i.write("></div>")};var a=function(j,l,S,A,v,I){j.write("<li");if(I){j.writeAttribute("id",I)}else{j.writeElementData(S)}var x=h(l,S);S.__stepName=x;var T=g(S);j.addClass("sapUiRoadMapContent");j.addClass("sapUiRoadMapStep");if(!S.getVisible()){j.addClass("sapUiRoadMapHidden")}if(S.getEnabled()){if(l.getSelectedStep()==S.getId()){j.addClass("sapUiRoadMapSelected")}}else{j.addClass("sapUiRoadMapDisabled")}if(A){for(var i=0;i<A.length;i++){j.addClass(A[i])}}j.writeClasses();j.write(">");b(j,I?I:S.getId(),1);j.write("<div");j.writeAttribute("id",(I?I:S.getId())+"-box");j.writeAttribute("tabindex","-1");j.addClass("sapUiRoadMapStepBox");j.writeClasses();j.writeAttributeEscaped("title",T);w(j,l,S,v?true:false);j.write("><span>");j.write(x);j.write("</span>");if(v){v(j,l,S)}j.write("</div>");j.write("<label");j.writeAttribute("id",(I?I:S.getId())+"-label");j.addClass("sapUiRoadMapTitle");j.writeAttributeEscaped("title",T);j.writeClasses();j.write(">");var L=S.getLabel();if(L){j.writeEscaped(L)}j.write("</label>");b(j,I?I:S.getId(),2);j.write("</li>")};var g=function(S){var T=S.getTooltip_AsString();if(!T){if(sap.ui.getCore().getConfiguration().getAccessibility()){T=k("RDMP_DEFAULT_STEP_TOOLTIP",[S.__stepName])}else{T=""}}return T};var b=function(i,I,j){i.write("<div");i.writeAttribute("id",I+"-add"+j);i.addClass("sapUiRoadMapStepAdd"+j);i.writeClasses();i.write("></div>")};var w=function(i,j,S,I){if(!sap.ui.getCore().getConfiguration().getAccessibility()){return}i.writeAttribute("role","treeitem");if(S.getEnabled()){i.writeAttribute("aria-checked",j.getSelectedStep()==S.getId())}else{i.writeAttribute("aria-disabled",true)}i.writeAttribute("aria-haspopup",I);i.writeAttribute("aria-level",S.getParent()instanceof sap.ui.commons.RoadMap?1:2);i.writeAttribute("aria-posinset",d(S));i.writeAttribute("aria-setsize",e(S));i.writeAttributeEscaped("aria-label",c(S,S.getLabel()));if(!I){return}i.writeAttribute("aria-expanded",S.getExpanded())};var c=function(S,l){var i=S.getParent()instanceof sap.ui.commons.RoadMap&&S.getSubSteps().length>0;var j=l||"";if(S.getEnabled()){j=k(i?"RDMP_ARIA_EXPANDABLE_STEP":"RDMP_ARIA_STANDARD_STEP",[j])}return j};var d=function(S){var I=S.getParent()instanceof sap.ui.commons.RoadMap;var j=S.getParent()[I?"indexOfStep":"indexOfSubStep"](S);var C=0;var l=S.getParent()[I?"getSteps":"getSubSteps"]();for(var i=0;i<j;i++){if(!l[i].getVisible()){C++}}return j+1-C};var e=function(S){var I=S.getParent()instanceof sap.ui.commons.RoadMap;var j=S.getParent()[I?"getSteps":"getSubSteps"]();var C=j.length;for(var i=0;i<j.length;i++){if(!j[i].getVisible()){C--}}return C};var f=function(j,l,S){var C=function(j,l,y,z,A){j.write("<div");j.writeAttribute("id",y+"-ico");j.addClass("sapUiRoadMapStepIco");if(A){j.addClass(A)}j.writeClasses();j.write("></div>")};var I=S.getExpanded();a(j,l,S,I?["sapUiRoadMapExpanded"]:null,function(j,l,S){C(j,l,S.getId(),I?"roundtripstart.gif":"roundtrip.gif")});var v=S.getSubSteps();for(var i=0;i<v.length;i++){var x=["sapUiRoadMapSubStep"];if(!I&&v[i].getVisible()){x.push("sapUiRoadMapHidden")}a(j,l,v[i],x)}x=["sapUiRoadMapExpanded","sapUiRoadMapStepEnd"];if(!I){x.push("sapUiRoadMapHidden")}a(j,l,S,x,function(j,l,S){C(j,l,S.getId()+"-expandend","roundtripend.gif")},S.getId()+"-expandend")};var h=function(i,S){var P=S.getParent();if(P===i){return P.indexOfStep(S)+1}var I=P.indexOfSubStep(S);if(I<26){return String.fromCharCode(97+I)}var j=Math.floor(I/26)-1;var l=I%26;return String.fromCharCode(97+j,97+l)};var u=function(i){var j=o();var S=i.$("steparea");var l=p(S);var v=i.$("Start");v.removeClass("sapUiRoadMapStartScroll").removeClass("sapUiRoadMapStartFixed");v.addClass(j*l>=i.iStepWidth?"sapUiRoadMapStartScroll":"sapUiRoadMapStartFixed");var E=i.$("End");E.removeClass("sapUiRoadMapEndScroll").removeClass("sapUiRoadMapEndFixed");var x=S.get(0).scrollWidth-j*l-S.width()<i.iStepWidth;E.addClass(x?"sapUiRoadMapEndFixed":"sapUiRoadMapEndScroll")};var k=function(K,A){var i=sap.ui.getCore().getLibraryResourceBundle("sap.ui.commons");if(i){return i.getText(K,A)}return K};var m=function(l){var v=0;var S=l.getSteps();for(var i=0;i<S.length;i++){if(S[i].getVisible()){v++;if(S[i].getExpanded()){v++;var x=S[i].getSubSteps();for(var j=0;j<x.length;j++){if(x[j].getVisible()){v++}}}}}return v};var n=function(S,j){var P=j.position().left;if(sap.ui.getCore().getConfiguration().getRTL()){P=S.width()-P-j.outerWidth()}return P};var o=function(){return sap.ui.getCore().getConfiguration().getRTL()&&!!!sap.ui.Device.browser.internet_explorer?-1:1};var p=function(S){if(sap.ui.getCore().getConfiguration().getRTL()&&!!sap.ui.Device.browser.webkit){return(-1)*(S.get(0).scrollWidth-S.scrollLeft()-S.width())}return S.scrollLeft()};var s=function(i,l){var S=i.$("steparea").get(0).scrollWidth;if(sap.ui.getCore().getConfiguration().getRTL()&&!!sap.ui.Device.browser.webkit){return l?0:(-1)*S}return l?S:0};var t=function(i,N,S,E){var j=i.$("steparea");j.stop(false,true);if(N=="next"){N=j.scrollLeft()+i.iStepWidth*o()}else if(N=="prev"){N=j.scrollLeft()-i.iStepWidth*o()}else if(N=="keep"){N=j.scrollLeft()}else{N=N*o()}var D=function(){u(i);if(E){var F=R.getFirstVisibleRef(i);E(F.attr("id"))}};if(!q.fx.off&&!S){j.animate({scrollLeft:N},"fast",D)}else{j.scrollLeft(N);D()}};return R},true);
