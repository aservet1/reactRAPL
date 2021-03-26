import React from 'react';
import { NavLink } from 'react-router-dom';

function Header () {
    return (
        <nav>
            <NavLink exact activeClassName="active" to="/energystats"> Energy Stats Snapshot | </NavLink>
            <NavLink exact activeClassName="active" to="/energydiff"> Energy Diff Over Time | </NavLink>
            <NavLink exact activeClassName="active" to="/dummy"> test component page | </NavLink>
            <NavLink exact activeClassName="active" to="/countdown"> count down </NavLink>
        </nav>
    );
}

export default Header;