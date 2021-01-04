import React from 'react';
import { NavLink } from 'react-router-dom';

function Header () {
    return (
        <nav>
            <NavLink exact activeClassName="active" to="/"> Energy Sampler </NavLink>
            <NavLink exact activeClassName="active" to="/dummy"> temporary </NavLink>
        </nav>
    );
}

export default Header;