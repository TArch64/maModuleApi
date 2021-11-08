<#-- @ftlvariable name="courseName" type="java.lang.String" -->
<#-- @ftlvariable name="recipientRole" type="java.lang.String" -->
<#-- @ftlvariable name="joinPath" type="java.lang.String" -->
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <style>
        .container {
            background-color: #000;
            width: 100%;
            max-width: 1024px;
            margin-left: auto;
            margin-right: auto;
        }
        .hero-image {
            display: block;
            max-width: 100%;
        }

        .main {
            background-color: #F9F9F9;
            padding: 124px 0;
            text-align: center;
            color: #000;
        }

        .heading { margin-bottom: 0 }

        .description {
            margin-top: 0;
            margin-bottom: 24px;
            font-size: 16px;
        }

        .join-link {
            display: block;
            width: 180px;
            margin: auto;
            padding: 16px 40px;
            background: #38343D;
            border-radius: 4px;
            color: #FFF!important;
            text-decoration: none;
            font-size: 24px;
            transition: opacity .15s easy-out;
        }
        .join-link:hover { opacity: 0.8 }

        .footer {
            padding: 16px 24px;
            background-color: #000;
            display: flex;
        }

        .footer-logo {
            margin-left: auto;
            width: 40px;
        }

        .footer-title {
            color: #FFF;
            display: inline-block;
            margin-left: 16px;
            margin-top: auto;
            margin-bottom: auto;
            font-size: 20px;
            padding-right: 16px;
        }
    </style>
</head>
<body>
    <div class="container">
        <img src="cid:ma-hero-image.jpg" alt="Welcome to MA Community" class="hero-image">

        <section class="main">
            <h1 class="heading">Welcome to MA Community</h1>
            <p class="description">You have been invited to <b>${courseName}</b></p>
            <a href="${joinPath}" class="join-link">Join Now</a>
        </section>

        <footer class="footer">
            <img src="cid:ma-logo-black.jpg" alt="MA logo" class="footer-logo">
            <p class="footer-title">Masters Academy</p>
        </footer>
    </div>
</body>
</html>
