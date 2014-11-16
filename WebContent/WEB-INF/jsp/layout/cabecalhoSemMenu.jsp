<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		
		<!-- CSS DO MENU E COR DE FUNDO-->
		<style>
			body{
				background: #eee url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAMAAAC6sdbXAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAAZQTFRF3d3d////riJKgAAAAAJ0Uk5T/wDltzBKAAAAFUlEQVR42mJgBAEGGMmAxAYCgAADAAGGABmnk/7aAAAAAElFTkSuQmCC);
				font: 13px 'trebuchet MS', Arial, Helvetica;
			}
			
			h2, p {
				text-align: center;
				color: #444;
				text-shadow: 0 1px 0 #fff;	
			}
			
			a {
				color: #2A679F;
			}
			
			/* You don't need the above styles, they are demo-specific ----------- */
			
			#menu, #menu ul {
				margin: 0;
				padding: 0;
				list-style: none;
			}
			
			#menu {
				width: 960px;
				margin: 00px auto; /*-----  Posicao do menu   -----*/
				border: 1px solid #222;
				background-color: #111;
				background-image: -moz-linear-gradient(#444, #111); 
				background-image: -webkit-gradient(linear, left top, left bottom, from(#444), to(#111));	
				background-image: -webkit-linear-gradient(#444, #111);	
				background-image: -o-linear-gradient(#444, #111);
				background-image: -ms-linear-gradient(#444, #111);
				background-image: linear-gradient(#444, #111);
				-moz-border-radius: 6px;
				-webkit-border-radius: 6px;
				border-radius: 6px;
				-moz-box-shadow: 0 1px 1px #777, 0 1px 0 #666 inset;
				-webkit-box-shadow: 0 1px 1px #777, 0 1px 0 #666 inset;
				box-shadow: 0 1px 1px #777, 0 1px 0 #666 inset;
			}
			
			#menu:before,
			#menu:after {
				content: "";
				display: table;
			}
			
			#menu:after {
				clear: both;
			}
			
			#menu {
				zoom:1;
			}
			
			#menu li {
				float: left;
				border-right: 1px solid #222;
				-moz-box-shadow: 1px 0 0 #444;
				-webkit-box-shadow: 1px 0 0 #444;
				box-shadow: 1px 0 0 #444;
				position: relative;
			}
			
			#menu a {
				float: left;
				padding: 12px 30px;
				color: #999;
				text-transform: uppercase;
				font: bold 12px Arial, Helvetica;
				text-decoration: none;
				text-shadow: 0 1px 0 #000;
			}
			
			#menu li:hover > a {
				color: #fafafa;
			}
			
			*html #menu li a:hover { /* IE6 only */
				color: #fafafa;
			}
			
			#menu ul {
				margin: 20px 0 0 0;
				_margin: 0; /*IE6 only*/
				opacity: 0;
				visibility: hidden;
				position: absolute;
				top: 38px;
				left: 0;
				z-index: 1;    
				background: #444;
				background: -moz-linear-gradient(#444, #111);
				background-image: -webkit-gradient(linear, left top, left bottom, from(#444), to(#111));
				background: -webkit-linear-gradient(#444, #111);    
				background: -o-linear-gradient(#444, #111);	
				background: -ms-linear-gradient(#444, #111);	
				background: linear-gradient(#444, #111);
				-moz-box-shadow: 0 -1px rgba(255,255,255,.3);
				-webkit-box-shadow: 0 -1px 0 rgba(255,255,255,.3);
				box-shadow: 0 -1px 0 rgba(255,255,255,.3);	
				-moz-border-radius: 3px;
				-webkit-border-radius: 3px;
				border-radius: 3px;
				-webkit-transition: all .2s ease-in-out;
				-moz-transition: all .2s ease-in-out;
				-ms-transition: all .2s ease-in-out;
				-o-transition: all .2s ease-in-out;
				transition: all .2s ease-in-out;  
			}

			#menu li:hover > ul {
				opacity: 1;
				visibility: visible;
				margin: 0;
			}
			
			#menu ul ul {
				top: 0;
				left: 150px;
				margin: 0 0 0 20px;
				_margin: 0; /*IE6 only*/
				-moz-box-shadow: -1px 0 0 rgba(255,255,255,.3);
				-webkit-box-shadow: -1px 0 0 rgba(255,255,255,.3);
				box-shadow: -1px 0 0 rgba(255,255,255,.3);		
			}
			
			#menu ul li {
				float: none;
				display: block;
				border: 0;
				_line-height: 0; /*IE6 only*/
				-moz-box-shadow: 0 1px 0 #111, 0 2px 0 #666;
				-webkit-box-shadow: 0 1px 0 #111, 0 2px 0 #666;
				box-shadow: 0 1px 0 #111, 0 2px 0 #666;
			}
			
			#menu ul li:last-child {   
				-moz-box-shadow: none;
				-webkit-box-shadow: none;
				box-shadow: none;    
			}
			
			#menu ul a {    
				padding: 10px;
				width: 130px;
				_height: 10px; /*IE6 only*/
				display: block;
				white-space: nowrap;
				float: none;
				text-transform: none;
			}
			
			#menu ul a:hover {
				background-color: #0186ba;
				background-image: -moz-linear-gradient(#04acec,  #0186ba);	
				background-image: -webkit-gradient(linear, left top, left bottom, from(#04acec), to(#0186ba));
				background-image: -webkit-linear-gradient(#04acec, #0186ba);
				background-image: -o-linear-gradient(#04acec, #0186ba);
				background-image: -ms-linear-gradient(#04acec, #0186ba);
				background-image: linear-gradient(#04acec, #0186ba);
			}
			
			#menu ul li:first-child > a {
				-moz-border-radius: 3px 3px 0 0;
				-webkit-border-radius: 3px 3px 0 0;
				border-radius: 3px 3px 0 0;
			}
			
			#menu ul li:first-child > a:after {
				content: '';
				position: absolute;
				left: 40px;
				top: -6px;
				border-left: 6px solid transparent;
				border-right: 6px solid transparent;
				border-bottom: 6px solid #444;
			}
			
			#menu ul ul li:first-child a:after {
				left: -6px;
				top: 50%;
				margin-top: -6px;
				border-left: 0;	
				border-bottom: 6px solid transparent;
				border-top: 6px solid transparent;
				border-right: 6px solid #3b3b3b;
			}
			
			#menu ul li:first-child a:hover:after {
				border-bottom-color: #04acec; 
			}
			
			#menu ul ul li:first-child a:hover:after {
				border-right-color: #0299d3; 
				border-bottom-color: transparent; 	
			}
			
			#menu ul li:last-child > a {
				-moz-border-radius: 0 0 3px 3px;
				-webkit-border-radius: 0 0 3px 3px;
				border-radius: 0 0 3px 3px;
			}
			
			/* Mobile */
			#menu-trigger {
				display: none;
			}

			@media screen and (max-width: 600px) {

				/* nav-wrap */
				#menu-wrap {
					position: relative;
				}

				#menu-wrap * {
					-moz-box-sizing: border-box;
					-webkit-box-sizing: border-box;
					box-sizing: border-box;
				}

				/* menu icon */
				#menu-trigger {
					display: block; /* show menu icon */
					height: 40px;
					line-height: 40px;
					cursor: pointer;		
					padding: 0 0 0 35px;
					border: 1px solid #222;
					color: #fafafa;
					font-weight: bold;
					background-color: #111;
					background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAPCAMAAADeWG8gAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBXaW5kb3dzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjE2QjAxNjRDOUNEOTExRTE4RTNFRkI1RDQ2MUYxOTQ3IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjE2QjAxNjREOUNEOTExRTE4RTNFRkI1RDQ2MUYxOTQ3Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MTZCMDE2NEE5Q0Q5MTFFMThFM0VGQjVENDYxRjE5NDciIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MTZCMDE2NEI5Q0Q5MTFFMThFM0VGQjVENDYxRjE5NDciLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz42AEtnAAAABlBMVEX///////9VfPVsAAAAAnRSTlP/AOW3MEoAAAAWSURBVHjaYmAgFzBiACKFho6NAAEGAD07AG1pn932AAAAAElFTkSuQmCC) no-repeat 10px center, -moz-linear-gradient(#444, #111); 
					background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAPCAMAAADeWG8gAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBXaW5kb3dzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjE2QjAxNjRDOUNEOTExRTE4RTNFRkI1RDQ2MUYxOTQ3IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjE2QjAxNjREOUNEOTExRTE4RTNFRkI1RDQ2MUYxOTQ3Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MTZCMDE2NEE5Q0Q5MTFFMThFM0VGQjVENDYxRjE5NDciIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MTZCMDE2NEI5Q0Q5MTFFMThFM0VGQjVENDYxRjE5NDciLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz42AEtnAAAABlBMVEX///////9VfPVsAAAAAnRSTlP/AOW3MEoAAAAWSURBVHjaYmAgFzBiACKFho6NAAEGAD07AG1pn932AAAAAElFTkSuQmCC) no-repeat 10px center, -webkit-linear-gradient(#444, #111);	
					background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAPCAMAAADeWG8gAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBXaW5kb3dzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjE2QjAxNjRDOUNEOTExRTE4RTNFRkI1RDQ2MUYxOTQ3IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjE2QjAxNjREOUNEOTExRTE4RTNFRkI1RDQ2MUYxOTQ3Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MTZCMDE2NEE5Q0Q5MTFFMThFM0VGQjVENDYxRjE5NDciIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MTZCMDE2NEI5Q0Q5MTFFMThFM0VGQjVENDYxRjE5NDciLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz42AEtnAAAABlBMVEX///////9VfPVsAAAAAnRSTlP/AOW3MEoAAAAWSURBVHjaYmAgFzBiACKFho6NAAEGAD07AG1pn932AAAAAElFTkSuQmCC) no-repeat 10px center, -o-linear-gradient(#444, #111);
					background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAPCAMAAADeWG8gAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBXaW5kb3dzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjE2QjAxNjRDOUNEOTExRTE4RTNFRkI1RDQ2MUYxOTQ3IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjE2QjAxNjREOUNEOTExRTE4RTNFRkI1RDQ2MUYxOTQ3Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MTZCMDE2NEE5Q0Q5MTFFMThFM0VGQjVENDYxRjE5NDciIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MTZCMDE2NEI5Q0Q5MTFFMThFM0VGQjVENDYxRjE5NDciLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz42AEtnAAAABlBMVEX///////9VfPVsAAAAAnRSTlP/AOW3MEoAAAAWSURBVHjaYmAgFzBiACKFho6NAAEGAD07AG1pn932AAAAAElFTkSuQmCC) no-repeat 10px center, -ms-linear-gradient(#444, #111);
					background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAPCAMAAADeWG8gAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBXaW5kb3dzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjE2QjAxNjRDOUNEOTExRTE4RTNFRkI1RDQ2MUYxOTQ3IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjE2QjAxNjREOUNEOTExRTE4RTNFRkI1RDQ2MUYxOTQ3Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MTZCMDE2NEE5Q0Q5MTFFMThFM0VGQjVENDYxRjE5NDciIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MTZCMDE2NEI5Q0Q5MTFFMThFM0VGQjVENDYxRjE5NDciLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz42AEtnAAAABlBMVEX///////9VfPVsAAAAAnRSTlP/AOW3MEoAAAAWSURBVHjaYmAgFzBiACKFho6NAAEGAD07AG1pn932AAAAAElFTkSuQmCC) no-repeat 10px center, linear-gradient(#444, #111);
					-moz-border-radius: 6px;
					-webkit-border-radius: 6px;
					border-radius: 6px;
					-moz-box-shadow: 0 1px 1px #777, 0 1px 0 #666 inset;
					-webkit-box-shadow: 0 1px 1px #777, 0 1px 0 #666 inset;
					box-shadow: 0 1px 1px #777, 0 1px 0 #666 inset;
				}
				
				/* main nav */
				#menu {
					margin: 0; padding: 10px;
					position: absolute;
					top: 40px;
					width: 100%;
					z-index: 1;
					background-color: #444;
					display: none;
					-moz-box-shadow: none;
					-webkit-box-shadow: none;
					box-shadow: none;		
				}

				#menu:after {
					content: '';
					position: absolute;
					left: 25px;
					top: -8px;
					border-left: 8px solid transparent;
					border-right: 8px solid transparent;
					border-bottom: 8px solid #444;
				}	

				#menu ul {
					position: static;
					visibility: visible;
					opacity: 1;
					margin: 0;
					background: none;
					-moz-box-shadow: none;
					-webkit-box-shadow: none;
					box-shadow: none;				
				}

				#menu ul ul {
					margin: 0 0 0 20px !important;
					-moz-box-shadow: none;
					-webkit-box-shadow: none;
					box-shadow: none;		
				}

				#menu li {
					position: static;
					display: block;
					float: none;
					border: 0;
					margin: 5px;
					-moz-box-shadow: none;
					-webkit-box-shadow: none;
					box-shadow: none;			
				}

				#menu ul li{
					margin-left: 20px;
					-moz-box-shadow: none;
					-webkit-box-shadow: none;
					box-shadow: none;		
				}

				#menu a{
					display: block;
					float: none;
					padding: 0;
					color: #999;
				}

				#menu a:hover{
					color: #fafafa;
				}	

				#menu ul a{
					padding: 0;
					width: auto;		
				}

				#menu ul a:hover{
					background: none;	
				}

				#menu ul li:first-child a:after,
				#menu ul ul li:first-child a:after {
					border: 0;
				}		

			}

			@media screen and (min-width: 600px) {
				#menu {
					display: block !important;
				}
			}	

			/* iPad */
			.no-transition {
				-webkit-transition: none;
				-moz-transition: none;
				-ms-transition: none;
				-o-transition: none;
				transition: none;
				opacity: 1;
				visibility: visible;
				display: none;  		
			}

			#menu li:hover > .no-transition {
				display: block;
			}
		</style>
		
		<!-- FUNCAO MENU -->
		<script type="text/javascript">
			$(function() {
				if ($.browser.msie && $.browser.version.substr(0,1)<7)
				{
				$('li').has('ul').mouseover(function(){
					$(this).children('ul').css('visibility','visible');
					}).mouseout(function(){
					$(this).children('ul').css('visibility','hidden');
					})
				}
	
				/* Mobile */
				$('#menu-wrap').prepend('<div id="menu-trigger">Menu</div>');		
				$("#menu-trigger").on("click", function(){
					$("#menu").slideToggle();
				});
	
				// iPad
				var isiPad = navigator.userAgent.match(/iPad/i) != null;
				if (isiPad) $('#menu ul').addClass('no-transition');      
			});          
		</script>
	
		<!-- CSS PARA AREDONDAR A DIV -->
		<style>
			.RoundedCorner {
				bbackground: ttransparent;

				font-family:Verdana, Arial, Helvetica, sans-serif;
				color:#666666;
				font-size:12px;
			}

			.RoundedCorner .b1, .RoundedCorner .b2, .RoundedCorner .b3, .RoundedCorner .b4,.RoundedCorner .b2Top, .RoundedCorner .b3Top, .RoundedCorner .b4Top,
			.RoundedCorner .b1b, .RoundedCorner .b2b, .RoundedCorner .b3b, .RoundedCorner .b4b {
				display:block;
				overflow:hidden;
				font-size:1px;
			}

			.RoundedCorner .b1, .RoundedCorner .b2, .RoundedCorner .b3,
			.RoundedCorner .b2Top, .RoundedCorner .b3Top,
			.RoundedCorner .b1b, .RoundedCorner .b2b, .RoundedCorner .b3b {
				height:1px;
			}

			.RoundedCorner .b2, .RoundedCorner .b3, .RoundedCorner .b4 {
				background:#FFFFFF;
				border-left:1px solid #999999;
				border-right:1px solid #999999;
			}

			.RoundedCorner .b1 {
				margin:0 5px; background:#999999;
			}

			.RoundedCorner .b2,.RoundedCorner .b2Top {
				margin:0 3px;
				border-width:0 2px;
			}

			.RoundedCorner .b3,.RoundedCorner .b3Top {
				margin:0 2px;
			}

			.RoundedCorner .b4,.RoundedCorner .b4Top {
				height:2px;
				margin:0 1px;
			}

			.RoundedCorner .RoundedCornerTitle{
				padding-left:5px;
				padding-right:5px;
				padding-top:0;
				padding-bottom:3;
				display:block;
				background:#FFFFFF;
				border-left:1px solid #999999;
				border-bottom:1px solid #999999;
				border-right:1px solid #999999;
				background:#D4E6FC;
				font-family:Verdana, Arial, Helvetica, sans-serif;
				font-size:14px;
				color:#224499;
				font-weight:bold;
			}

			.RoundedCorner .RoundedCornerContent {
				display:block;
				background:#FFFFFF;
				border-left:1px solid #999999;
				border-right:1px solid #999999;
			}

			.RoundedCorner .b2Top, .RoundedCorner .b3Top, .RoundedCorner .b4Top {
				background:#D4E6FC;
				border-left:1px solid #999999;
				border-right:1px solid #999999;
			}
		</style>
		
		<!-- EFEITOS DO INPUT TEXTAREA E SELECT -->
		<style type="text/css">
			input[type=text]{ 
				border-radius:4px;
				-moz-border-radius:4px;
				-webkit-border-radius:4px;
				box-shadow: 1px 1px 2px #333333;
				-moz-box-shadow: 1px 1px 2px #333333;
				-webkit-box-shadow: 1px 1px 2px #333333;
				background: -moz-linear-gradient(center top , #FFFFFF, #EEEEEE 1px, #FFFFFF 25px) repeat scroll 0 0 transparent;
				-moz-box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
				
				height: 24px;
				border:1px solid #000000;
				
			}
			
			select{ 
				border-radius:4px;
				-moz-border-radius:4px;
				-webkit-border-radius:4px;
				box-shadow: 1px 1px 2px #333333;
				-moz-box-shadow: 1px 1px 2px #333333;
				-webkit-box-shadow: 1px 1px 2px #333333;
				background: -moz-linear-gradient(center top , #FFFFFF, #EEEEEE 1px, #FFFFFF 25px) repeat scroll 0 0 transparent;
				-moz-box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
				
				border:1px solid #000000;
				
			}

			textarea{
				border: 1px solid #000000;
				background: -moz-linear-gradient(center top , #FFFFFF, #EEEEEE 1px, #FFFFFF 25px) repeat scroll 0 0 transparent;
					-moz-box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
				width:150px;
				height:100px;
				border-radius:4px;
				-moz-border-radius:4px;
				-webkit-border-radius:4px;
				box-shadow: 1px 1px 2px #333333;
				-moz-box-shadow: 1px 1px 2px #333333;
				-webkit-box-shadow: 1px 1px 2px #333333;
			}

			input[type=text]:hover, textarea:hover{ 
					 background: #ffffff; border:1px solid #000000;
			}

		</style>
		
		
		<!-- RODAPE -->
		<style type="text/css">
			body {
			   margin:0;
			   padding:0;
			   height:100%;
			}
			.tudo {
			   min-height:100%;
			   position:relative;
			}
			.rodape {
			   position:absolute;
			   bottom:0;
			   width:100%;
			   height:25px;
			   background:blue;
			}
		</style>
		
		
		<!-- SCRIPT PARA JANELA MODAL -->
		<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.mim.js"></script> 
		
		<!-- SCRIPT PARA JANELA MODAL -->
		<script type="text/javascript">
			$(document).ready(function() {	

				$('a[name=modal]').click(function(e) {
					e.preventDefault();
					
					var id = $(this).attr('href');
				
					var maskHeight = $(document).height();
					var maskWidth = $(window).width();
				
					$('#mask').css({'width':maskWidth,'height':maskHeight});

					$('#mask').fadeIn(1000);	
					$('#mask').fadeTo("slow",0.8);	
				
					//Get the window height and width
					var winH = $(window).height();
					var winW = $(window).width();
						  
					$(id).css('top',  winH/2-$(id).height()/2);
					$(id).css('left', winW/2-$(id).width()/2);
				
					$(id).fadeIn(2000); 
				
				});
				
				$('.window .close').click(function (e) {
					e.preventDefault();
					
					$('#mask').hide();
					$('.window').hide();
				});		
				
				$('#mask').click(function () {
					$(this).hide();
					$('.window').hide();
				});			
				
			});

		</script>
		
		<!-- CSS PARA JANELA MODAL -->
		<style type="text/css">
			#mask {
			  position:absolute;
			  left:0;
			  top:0;
			  z-index:9000;
			  background-color:#000;
			  display:none;
			}
			  
			#boxes .window {
			  position:absolute;
			  left:0;
			  top:0;
			  width:440px;
			  height:200px;
			  display:none;
			  z-index:9999;
			  padding:20px;
			}

			#boxes #dialog {
			  width:375px; 
			  height:203px;
			  padding:10px;
			  background-color:#ffffff;
			}

			#boxes #dialog1 {
			  width:375px; 
			  height:203px;
			}

			#dialog1 .d-header {
			  background:url(login-header.png) no-repeat 0 0 transparent; 
			  width:375px; 
			  height:150px;
			}

			#dialog1 .d-header input {
			  position:relative;
			  top:60px;
			  left:100px;
			  border:3px solid #cccccc;
			  height:22px;
			  width:200px;
			  font-size:15px;
			  padding:5px;
			  margin-top:4px;
			}

			#dialog1 .d-blank {
			  float:left;
			  background:url(login-blank.png) no-repeat 0 0 transparent; 
			  width:267px; 
			  height:53px;
			}

			#dialog1 .d-login {
			  float:left;
			  width:108px; 
			  height:53px;
			}

			#boxes #dialog2 {
			  background:url(notice.png) no-repeat 0 0 transparent; 
			  width:326px; 
			  height:229px;
			  padding:50px 0 20px 25px;
			}
						
			.close{display:block; text-align:right;}

		</style>

		<title>
			Safety &#38; Car
		</title>
		
	</head>
		
	<body>
	<div class="tudo">
		<br/>
		<!-- LOGOS -->
		<div>
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="960px">
				<tr>
					<td>						
						<table align="center" border="0" cellpadding="0" cellspacing="0" width="958px">
							<tr>
								<td>
									<img align="left" width="200" height="50" src="/safetycar/resources/imagens/logo_Evolution.png"/>
								<td>
								<td>
									<img align="right" width="75" height="45" src="/safetycar/resources/imagens/logo_Safety&Car.png"/>
								<td>
							</tr>			
						</table>							
					<td>
				<tr>
			</table>
		</div>			
		
		<!-- ESPACO ENTRE O LOGO E O MENU -->
		<font size="1" color="black">
			&nbsp;
		</font>		
	
		&nbsp;
		
		<!-- CONTEUDO DO SISTEMA -->
		<div>
			<table align="center" valign="top" border="0" cellpadding="0" cellspacing="0" width="960px">
				<tr>
					<td>
						<div class="RoundedCorner" style="width:960px;">
							<b class="b1"></b>
							<b class="b2"></b>
							<b class="b3"></b>
							<b class="b4"></b>
							<div class="RoundedCornerContent" >
								<br/>
								<!-- CONTEUDO DO SISTEMA -->
								
<!-- ################################################################################################################################################## -->	
								