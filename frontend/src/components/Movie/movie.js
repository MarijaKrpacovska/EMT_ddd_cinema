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
                            <th scope={"col"}>Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.movies.map((term) => {
                            return (
                                <tr>
                                    <td>{term.id.id}</td>
                                    <Link className={"btn btn-info ml-2"}
                                       onClick={() => props.onDetails(term.id.id)}
                                       to={`/movie/findById/${term.id.id}`}>
                                        Details
                                    </Link>
                                    <Link className={"btn btn-info ml-2"}
                                          onClick={() => props.onScheduleMovie(term.id.id)}
                                          to={`/movie/scheduleMovie/${term.id.id}`}>
                                        Details
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
                        <Link className={"btn btn-block btn-dark"} to={"/movie/add"}>Add new product</Link>
                    </div>
                </div>
            </div>

            <div className="col mb-3">
                <div className="row">
                    <div className="col-sm-12 col-md-12">
                        <Link className={"btn btn-block btn-dark"} to={"/ticket/makeReservation"}>Add new product</Link>
                    </div>
                </div>
            </div>

        </div>
    );
}

export default movies;
