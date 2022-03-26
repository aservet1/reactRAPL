import './Header.css'

import React from 'react';
import { NavLink } from 'react-router-dom';

function Header () {
    return (
        <div id="the-header">
            <nav>
                <NavLink className="header-link" exact activeClassName="active" to="/energystats">   Energy Stats Snapshot    </NavLink>
                <NavLink className="header-link" exact activeClassName="active" to="/energydiff">    Energy Diff Over Time    </NavLink>
                <NavLink className="header-link" exact activeClassName="active" to="/energyseries">  Energy Series Over Time  </NavLink>
                <NavLink className="header-link" exact activeClassName="active" to="/liveupdates">   Live Updates             </NavLink>
                <NavLink className="header-link" exact activeClassName="active" to="/dummy">         test component page      </NavLink>
                <NavLink className="header-link" exact activeClassName="active" to="/countdown">     count down               </NavLink>
            </nav>
        </div>
    );
}

export default Header;