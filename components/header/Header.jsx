import EldoradoLogo from '../../assets/images/EldoradoLogo.png';

const Header = () => {
    return(
        <div className="header container-fluid bg-light text-center">
            <span><img src={EldoradoLogo} alt=""/></span>
        </div>
    )
}

export default Header;