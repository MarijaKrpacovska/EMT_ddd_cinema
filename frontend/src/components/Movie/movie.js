import './movie.css';
import React from "react";
import {Link} from 'react-router-dom';
//
// <Link className={"btn btn-info ml-2"}
//       onClick={() => props.onActiveReservation()}
//       to={`/ticket/activeReservation`}>
//     Shopping Cart
// </Link>

var image = document.getElementsByClassName("imgImg");
var currentPos = 0;
var images = ["https://movies.universalpictures.com/media/us-adv1sheet-rgb-2-small-5c1c422026bb0-1-5d3a4e5f87325-1.jpg",
    "https://assets.mubicdn.net/images/notebook/post_images/33421/images-w1400.jpg?1625400709"]

function volgendefoto() {
    if (++currentPos >= images.length)
        currentPos = 0;

    image.src = images[currentPos];
}

setInterval(volgendefoto, 1);

const movies = (props) => {
    //console.log("SELECTED MOVIE" +props.movies(0).id.id)
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                <div className={"row"}>
                    <div className={"col-md-8"}>
                        <h1 className={"text-danger"}>
                            Current Movies
                        </h1>
                        <hr/>
                    <table className={"table"}>
                        <thead>
                        </thead>
                        <tbody>
                        {props.movies.map((term) => {
                            var myDate = new Date(term.publishDate.toString());
                            return (
                                <tr className={"p-1 mt-5"}>

                                    <Link
                                        onClick={() => props.onDetails(term.id.id)}
                                        to={`/movie/findById/${term.id.id}`}>
                                        <img className={"mt-1 mb-1"} src={term.url} height={"200px"} width={"136px"}></img>
                                    </Link>
                                    <td className={"firstTd"}>
                                        <h3 className={"mb-3"}>
                                        {term.name} <small className={"font-weight-light movieGenre"}>({term.genre})</small>
                                        </h3>
                                        {term.description}
                                        <br/>
                                        <b>Length:</b> {term.movieLength.length} {term.movieLength.unitOfTime}
                                        <br/>
                                        <b>Released on:</b> {myDate.toString().substr(0,15)}
                                        <br/>
                                        <br/>
                                        <div className={"row text-center"}>
                                            <Link className={"btn scheduleMovieButton"}
                                                  onClick={() => props.onScheduleMovie(term.id.id)}
                                                  to={`/scheduledMovies/add/${term.id.id}`}>
                                                Schedule movie
                                            </Link>
                                        </div>
                                    </td>
                                </tr>
                            );
                        })}
                        <tr></tr>
                        </tbody>
                    </table>
                    </div>
                    <div className={"col-md-4 imgDiv"}>
                        <img className={"imgImg"} height={"1300px"} src={"https://movies.universalpictures.com/media/us-adv1sheet-rgb-2-small-5c1c422026bb0-1-5d3a4e5f87325-1.jpg"}/>
                    </div>
                </div>
                </div>
            </div>

            <div className="col mb-3">
                <div className="row">
                    <div className="col-sm-12 col-md-12 buttonDiv">
                        <Link className={"btn btn-lg btn-block btn-dark"} to={"/movie/add"}>Add Movie</Link>
                    </div>
                </div>
            </div>

        </div>
    );
}

export default movies;
