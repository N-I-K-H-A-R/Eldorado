import Select from 'react-select';
import EldoradoLogo from '../../assets/images/EldoradoLogo.png';
import Profile from '../../assets/images/Profile.png';
import { Nav, Navbar, NavDropdown, Button, FormControl, InputGroup, Col } from "react-bootstrap";
import './Navigationbar.css'
import 'font-awesome/css/font-awesome.min.css';

const Categories = [
    {label: 'Groceries', value: 'Groceries'},
    {label: 'Clothes', value: 'Clothes'},
    {label: 'Books', value: 'Books'},
    {label: 'Medicines', value: 'Medicines'},
    {label: 'Fruits', value: 'Fruits'}
]

const Navigationbar = () => {
    return(
        <div className="navigation-bar">
            <Navbar collapseOnSelect expand="lg" bg="light">
                <div className="boxs w-20">
                    <Navbar.Brand to="#"><img className="eldoradoLogo" src={EldoradoLogo} alt=""/></Navbar.Brand>
                </div>
                
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav" className="fixnavbar w-80">
                    <Nav className="col-8">
                        <Col xs={12} sm={12} md={12} lg={12}>
                            <InputGroup>
                                <Select placeholder="Search by Category" className="col-xs-1 col-sm-1 col-md-1 col-lg-3" defaultValue={{ label: "All", value: "All" }} options={Categories} />
                                <FormControl type="search" placeholder="Search" className="col-xs-1 col-sm-1 col-md-1 col-lg-3"  aria-label="Search" />
                                <Button className="search-icon"><i className="fa fa-search"></i></Button>
                            </InputGroup>
                        </Col>
                    </Nav>
                    <Nav pullRight>
                        <NavDropdown title={<img className="proPic" src={Profile} alt=""/>} id="navbarScrollingDropdown">
                            <NavDropdown.Item to="/profile">My Profile</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item to="/logout">Logout</NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        </div>
    )};

export default Navigationbar;