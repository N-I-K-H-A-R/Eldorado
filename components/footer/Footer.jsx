import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css' 
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
faGoogle,
faFacebook,
faTwitter,
faInstagram
} from '@fortawesome/free-brands-svg-icons';
import './Footer.css'
const FooterPage = () => {
return (
<footer class="page-footer font-small container-fluid bg-light pt-4 ">
    <div class="container-fluid text-center bg-light ">
        <div class="list-unstyled ">
            <a href="/#" class="text-blue mr-3 "> <FontAwesomeIcon  id="facebook-icon" icon={faFacebook} /></a>  
            <a href="/#" class="google text-green mr-3"><FontAwesomeIcon icon={faGoogle} /></a>      
            <a href="/#" class="text-blue mr-3"><FontAwesomeIcon icon={faTwitter} /></a>  
            <a href="/#" class="insta text-blue mr-3"><FontAwesomeIcon icon={faInstagram} /></a>  
        </div>
        <div class="foot footer-copyright text-center py-3">© 2021 Copyright:
            <a class="copy "> eldorado.com</a>
        </div>
    </div>
</footer>
);
}

export default FooterPage;