import './movie.css';
import React from "react";
import {Link} from 'react-router-dom';
//
// <Link className={"btn btn-info ml-2"}
//       onClick={() => props.onActiveReservation()}
//       to={`/ticket/activeReservation`}>
//     Shopping Cart
// </Link>



const movies = (props) => {

    // const image = document.getElementById("imgImg");
    // const currentPos = 1;
    // const images = ["https://movies.universalpictures.com/media/us-adv1sheet-rgb-2-small-5c1c422026bb0-1-5d3a4e5f87325-1.jpg",
    //     "https://assets.mubicdn.net/images/notebook/post_images/33421/images-w1400.jpg?1625400709"]
    //
    // //console.log("SELECTED MOVIE" +props.movies(0).id.id)
    // function volgendefoto() {
    //     const images = ["https://movies.universalpictures.com/media/us-adv1sheet-rgb-2-small-5c1c422026bb0-1-5d3a4e5f87325-1.jpg",
    //         "https://assets.mubicdn.net/images/notebook/post_images/33421/images-w1400.jpg?1625400709",
    //         "https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg"]
    //
    //     let index = Math.floor(Math.random() * (2 + 1));
    //     console.log(index);
    //
    //     document.getElementById("imgImg").src = images[index];
    // }
    //
    // setInterval(volgendefoto, 10000);

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
                                            <Link
                                                onClick={() => { props.onDetails(term.id.id); props.onFetchScheduledMoviesByMovieId(term.id.id)}}
                                                to={`/movie/fetchScheduledMoviesByMovieId/${term.id.id}`}>
                                                Details with sm
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
                        <img id={"imgImg"} className={"imgImg"} height={"1600px"} src={"https://movies.universalpictures.com/media/us-adv1sheet-rgb-2-small-5c1c422026bb0-1-5d3a4e5f87325-1.jpg"}/>
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
