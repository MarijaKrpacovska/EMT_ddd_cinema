import React from "react";
import './movieDetailsWithScheduledMovies.css'
import {Link} from 'react-router-dom';

const movieDetailsWithScheduledMovies = (props) => {

    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <div className={"row"}>

                        <div className={"row"}>
                            <div className={"col-md-4"}>
                                <img src={props.selectedMovie.url} height={"540px"}></img>
                            </div>
                            <div className={"col-md-8"}>
                                <h1 className={"text-dark nameOfMovie"}>
                                    {props.selectedMovie.name}
                                    ({props.selectedMovie.genre})
                                </h1>
                                <h5 className={"mt-3"}>{props.selectedMovie.description}</h5>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <div className={"row"}>
                                    <p>Book tickets:</p>
                                    {props.scheduledMovies.map((term) => {

                                        return (
                                            <div className={"col"}>
                                                <Link className={"btn btn-lg btn-block btn-dark"}
                                                    onClick={() => props.onBookTickets(term.id.id)}
                                                    to={`/ticket/makeNewReservation/${term.id.id}`}>
                                                    Monday, {term.startTime.hour}:{term.startTime.minutes} - {term.endTime.hour}:{term.endTime.minutes}
                                                </Link>
                                            </div>
                                        );
                                    })}
                                </div>
                            </div>

                            <hr/>
                        </div>
                            {/*<table className={"table"}>*/}
                            {/*    <thead>*/}
                            {/*    </thead>*/}
                            {/*    <tbody>*/}
                            {/*    <td></td>*/}
                            {/*    {props.scheduledMovies.map((term) => {*/}
                            {/*        return (*/}
                            {/*            <tr className={"p-1 mt-5"}>*/}

                            {/*                <td className={"firstTd"}>*/}
                            {/*                    <h3 className={"mb-3"}>*/}
                            {/*                        {term.id.id} <small className={"font-weight-light movieGenre"}>({term.genre})</small>*/}
                            {/*                    </h3>*/}
                            {/*                </td>*/}
                            {/*            </tr>*/}
                            {/*        );*/}
                            {/*    })}*/}
                            {/*    <tr></tr>*/}
                            {/*    </tbody>*/}
                            {/*</table>*/}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default movieDetailsWithScheduledMovies;