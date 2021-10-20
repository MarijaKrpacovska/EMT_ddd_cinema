import './header.css'
import React from 'react';
import {Link} from 'react-router-dom';

const header = (props) => {
    return (
        <header>

            <nav className="navbar navbar-expand-md navbar-dark navbar-fixed bg-danger">
                <div className="container">
                    <Link className={"homePageLink"} to={"/home"}>
                        <div className={"row homePageLinkDiv"}>
                            <div className={"col"}>
                                <img height={"50px"} src={"https://www.pngkit.com/png/full/393-3931522_cinema-png-download-film-tape.png"}/>
                            </div>
                            <div className={"col homePageLinkText"}>
                                <i className="navbar-brand cinemaText"> <b>Cinema</b></i>
                            </div>
                        </div>
                    </Link>
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
                            <li className="nav-item my-lg-0 text-danger sredinaLi">
                                <a className="nav-link" href="#">
                                    <div className={"divZaProstor"}></div>
                                </a>
                            </li>

                            <li className="nav-item active">
                                <Link className="nav-link" to={"/ticket/confirmedReservations"}>Reservations</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>



        </header>
    )
}

export default header;
