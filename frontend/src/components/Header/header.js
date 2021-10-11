import './header.css'
import React from 'react';
import {Link} from 'react-router-dom';

const header = (props) => {
    return (
        <header>

            <nav className="navbar navbar-expand-md navbar-dark navbar-fixed bg-danger">
                <div className="container">
                    <img height={"50px"} src={"https://www.pngkit.com/png/full/393-3931522_cinema-png-download-film-tape.png"}/>
                    <i className="navbar-brand"> <b>Cinema</b></i>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarCollapse">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item active">
                                <Link className="nav-link" to={"/movie"}>Movies</Link>
                            </li>
                            <li className="nav-item active">
                                <Link className="nav-link" to={"/scheduledMovies"}>Scheduled Movies</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

        </header>
    )
}

export default header;
