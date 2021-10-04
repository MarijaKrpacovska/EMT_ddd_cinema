import React from "react";
import {Link} from 'react-router-dom';

const movies = (props) => {
    //console.log("SELECTED MOVIE" +props.movies(0).id.id)
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>url</th>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Description</th>
                            <th scope={"col"}>Genre</th>
                            <th scope={"col"}>PublishDate</th>
                            <th scope={"col"}>Length</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.movies.map((term) => {
                            return (
                                <tr>
                                    <img src={term.url} height={"140px"}></img>
                                    <td>
                                        <Link
                                              onClick={() => props.onDetails(term.id.id)}
                                              to={`/movie/findById/${term.id.id}`}>
                                            {term.name}
                                        </Link>
                                    </td>
                                    <td>{term.description}</td>
                                    <td>{term.genre}</td>
                                    <td>{term.publishDate}</td>
                                    <td>{term.movieLength.length} {term.movieLength.unitOfTime}</td>


                                    <Link className={"btn btn-info ml-2"}
                                          onClick={() => props.onScheduleMovie(term.id.id)}
                                          to={`/movie/scheduleMovie/${term.id.id}`}>
                                        Schedule movie
                                    </Link>
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
                        <Link className={"btn btn-block btn-dark"} to={"/movie/add"}>Add new Movie</Link>
                    </div>
                </div>
            </div>

            <div className="col mb-3">
                <div className="row">
                    <div className="col-sm-12 col-md-12">
                        <Link className={"btn btn-block btn-dark"} to={"/ticket/makeReservation"}>Make Reservation</Link>
                    </div>
                </div>
            </div>

        </div>
    );
}

export default movies;
