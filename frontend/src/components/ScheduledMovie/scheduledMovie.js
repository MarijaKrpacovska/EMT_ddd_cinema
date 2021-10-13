import React from "react";
import movies from "../Movie/movie";
import {Link} from 'react-router-dom';

const scheduledMovie = (props) => {
    //console.log("SELECTED MOVIE" +props.movies(0).id.id)
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>sales</th>
                            <th scope={"col"}>time</th>
                            <th scope={"col"}>ticket price</th>
                            <th scope={"col"}>movie id</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>

                        {props.scheduledMovies.map((term) => {
                            return (
                                <tr>
                                    <td>{term.sales}</td>
                                    <td>{term.startTime.hour}:{term.startTime.minutes} - {term.endTime.hour}:{term.endTime.minutes}</td>
                                    <td>{term.ticketsPrice.amount} {term.ticketsPrice.currency}</td>
                                    <td>{term.movieId.id}</td>
                                    <td>{term.scheduledMovieStatus}</td>
                                    <td>
                                        <Link
                                            onClick={() => props.onBookTickets(term.id.id)}
                                            to={`/ticket/makeNewReservation/${term.id.id}`}>
                                            Book tickets
                                        </Link>
                                    </td>
                                    <td>
                                        <Link
                                            onClick={() => { props.onCancelScheduledMove(term.id.id)}}
                                            to={`/scheduledMovies`}>
                                            Cancel scheduled movie
                                        </Link>
                                    </td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>

            <div className="col mb-3">
                <div className="row">
                    <div className="col-sm-12 col-md-12">
                    </div>
                </div>
            </div>

            <div className="col mb-3">
                <div className="row">
                    <div className="col-sm-12 col-md-12">
                    </div>
                </div>
            </div>

            <div className={"col-md-4 imgDiv"} hidden={"true"}>
                <img id={"imgImg"} className={"imgImg"} height={"1130px"} src={"https://movies.universalpictures.com/media/us-adv1sheet-rgb-2-small-5c1c422026bb0-1-5d3a4e5f87325-1.jpg"}/>
            </div>

        </div>
    );
}

export default scheduledMovie;