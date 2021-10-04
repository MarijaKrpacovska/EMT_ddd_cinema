import React from "react";
import movies from "../Movie/movie";

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

        </div>
    );
}

export default scheduledMovie;