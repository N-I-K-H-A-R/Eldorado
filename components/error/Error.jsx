import React from 'react';
import { Container, Row, Image, Col} from 'react-bootstrap';
import ErrImg from '../../assets/images/error2.png';
import { Link } from 'react-router-dom';
import './Error.css';
const Error = () => {
    return (
        <Container className="justify-content-center">
            <Row className="justify-content-center">
                <Col md={6} className="text-center" >
                    <Image src={ErrImg} id="errorimg" alt="errorimg"/>
                </Col>
                <Col md={6} className="text-center" >
                    <div className="errormsg">
                        <h1 id="error-heading">Oops! 404</h1>
                        <p className="error-paragraph">That Page Couldn't be Found</p>
                        <div className="btn-holder">
                            <Link to="/"><button className="btnnew success">Home</button></Link>
                        </div>
                    </div>
                </Col>
            </Row>
        </Container>
    )
}

export default Error;
