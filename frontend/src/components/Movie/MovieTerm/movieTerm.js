import React from "react";
import {Link} from 'react-router-dom';

const movieTerm = (props) => {
    var myDate = new Date(props.term.publishDate.toString());
    return(

        <tr className={"p-1 mt-5"}>

            <Link
                onClick={() => {
                    props.onDetails(props.term.id.id);
                    props.onFetchScheduledMoviesByMovieId(props.term.id.id)
                }}
                to={`/movie/fetchScheduledMoviesByMovieId/${props.term.id.id}`}>
                <img className={"mt-1 mb-1"} src={props.term.url} height={"200px"}
                     width={"136px"}>

                </img>
            </Link>
            <td className={"firstTd"}>
                <Link
                    className={"text-black detailsLink"}
                    onClick={() => {
                        props.onDetails(props.term.id.id);
                        props.onFetchScheduledMoviesByMovieId(props.term.id.id)
                    }}
                    to={`/movie/fetchScheduledMoviesByMovieId/${props.term.id.id}`}>
                    <div>
                        <h3 className={"mb-3"}>
                            {props.term.name} <small
                            className={"font-weight-light movieGenre"}>({props.term.genre})</small>
                        </h3>
                        {props.term.description}
                        <br/>
                        <b>Length:</b> {props.term.movieLength.length} {props.term.movieLength.unitOfTime}
                        <br/>
                        <b>Released on:</b> {myDate.toString().substr(0, 15)}
                        <br/>
                        <br/>
                        <div className={"row text-center"}>

                            <div className={"col-sm-6 col-md-6"}></div>
                            <div className="col-sm-3 col-md-3 buttonDiv">
                                <Link
                                    className={"btn btn-sm btn-block btn-dark scheduleMovieButton"}
                                    onClick={() => props.onScheduleMovie(props.term.id.id)}
                                    to={`/scheduledMovies/add/${props.term.id.id}`}>
                                    Schedule movie
                                </Link>
                            </div>
                            <div className="col-sm-3 col-md-3 text-sm">
                                <i>Currently
                                    scheduled {props.term.numberOfTimesScheduled} times</i>
                            </div>
                        </div>
                    </div>
                </Link>
            </td>
        </tr>
    );
}

export default movieTerm;