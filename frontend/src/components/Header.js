import React from 'react';
import { NavLink } from 'react-router-dom';

function Header () { // TODO get some CSS on to this header ;)
    return (
        <nav>
            <NavLink exact activeClassName="active" to="/energystats"> Energy Stats Snapshot | </NavLink>
            <NavLink exact activeClassName="active" to="/energydiff"> Energy Diff Over Time | </NavLink>
            <NavLink exact activeClassName="active" to="/energyseries"> Energy Series Over Time | </NavLink>
            <NavLink exact activeClassName="active" to="/dummy"> test component page | </NavLink>
            <NavLink exact activeClassName="active" to="/countdown"> count down </NavLink>
        </nav>
    );
}

export default Header;