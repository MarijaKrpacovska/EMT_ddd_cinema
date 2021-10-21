import React from "react";
import './movieDetailsWithScheduledMovies.css'
import {Link} from 'react-router-dom';

const movieDetailsWithScheduledMovies = (props) => {

    const showRating = (movie) => {

        console.log(movie?.rating?.rating);
        const stars = [];
        for (let i = 0; i < movie?.rating?.rating; i++) {
            stars.push(<img height={"24px"} src={"https://i.imgur.com/fhFxIiJ.png"}></img>);
        }
        for (let i = Math.ceil(movie?.rating?.rating); i < 10; i++) {
            stars.push(<img height={"24px"} src={"https://i.imgur.com/fwE9Dya.png"}></img>);
        }
        console.log(stars);
        return <div className="stars">{stars}</div>;
        //return <p>bla</p>;
    };

    return (
        <div className={"container mm-4 mt-5"}>

            <div className={"row"}>
                <div className={"row"}>
                    <section>
                        <div className={"row"}>

                            <div className={"col-md-4"}>
                                <img src={props?.selectedMovie?.moviePoster?.imageUrl} height={"490px"}></img>
                            </div>
                            <div className={"col-md-8"}>
                                <div className={"row"}>
                                    <div className={"col-md-7"}>
                                        <h1 className={"text-dark nameOfMovie"}>
                                            {props?.selectedMovie?.name}
                                        </h1>
                                    </div>
                                    <div className={"col-md-5 text-right"}>
                                        {showRating(props?.selectedMovie)}
                                    </div>
                                </div>
                                <p className={"mt-3"}>
                                    {props?.selectedMovie?.description}
                                </p>
                                <hr/>

                                <p className={"mt-3"}>
                                    <b>Genre: </b>{props?.selectedMovie?.genre}
                                </p>
                                <p className={"mt-3"}>
                                    <b>Length: </b>{props?.selectedMovie?.movieLength?.length} {props?.selectedMovie?.movieLength?.unitOfTime}
                                </p>
                                <p className={"mt-3"}>
                                    <b>Release date: </b>{props?.selectedMovie?.publishDate}
                                </p>
                                <hr/>
                                <div className={"row"}>
                                    <h4 className={"text-danger mb-2"}>Book tickets:</h4>
                                    <br/>
                                    {props?.scheduledMovies?.length !== 0 ? props?.scheduledMovies?.map((term) => {
                                            return (
                                                <div className={"col"}>
                                                    <Link className={"btn btn-lg btn-block btn-dark"}
                                                          onClick={() => props?.onBookTickets(term?.id?.id)}
                                                          to={`/ticket/makeNewReservation/${term?.id?.id}`}>
                                                        {term?.dateAndTimeScheduled?.date}, {term?.dateAndTimeScheduled?.hour}:{term?.dateAndTimeScheduled?.minutes}
                                                    </Link>
                                                </div>
                                            );
                                        }) :
                                        <p> This movie isn't scheduled for showing yet. Please pick another movie
                                            from <a className={"theListOfMoviesLink"} href={"/movie"}>the movie
                                                program</a>, or come back later.</p>}
                                </div>
                            </div>
                        </div>
                    </section>
                    <br className={"mt-5"}/>
                    <br/>
                    <section className={"trailerSection mt-5 mb-5"}>
                        <div className={"row"}>
                            <div className={"col-md-3"}>
                                <div className={"row watchTrailerRow"}>
                                    <div className={"col-md-9"}>
                                        <h5>Watch trailer: </h5>
                                    </div>
                                    <div className={"col-md-3 borderLine"}>

                                    </div>
                                </div>
                                <br/>
                            </div>
                            <div className={"col-md-9"}>
                                <div className={"row"}>
                                    <iframe height="500" src={props?.selectedMovie?.trailerUrl}>
                                    </iframe>
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </section>

                    <section className={"similarMoviesSection mt-5 mb-5"}>
                        <div className={"row"}>
                            <div className={"col-md-3"}>
                                <div className={"row watchTrailerRow"}>
                                    <div className={"col-md-9"}>
                                        <h5>Similar movies: </h5>
                                    </div>
                                    <div className={"col-md-3 borderLine"}>

                                    </div>
                                </div>
                                <br/>
                            </div>
                            <div className={"col-md-9"}>
                                <div className={"row"}>
                                    {props?.similarMovies?.length !== 0 ? props?.similarMovies?.map((term) => {
                                            return (
                                                <div className={"col-md-3"}>
                                                    <Link onClick={() => {
                                                        props.onDetails(term?.id?.id);
                                                        props.onFetchScheduledMoviesByMovieId(term?.id?.id)
                                                        props.onFindSimilarMovies(term?.genre)
                                                    }}
                                                          to={`/movie/fetchScheduledMoviesByMovieId/${term?.id?.id}`}>
                                                        <img width={"220px"} src={term?.moviePoster?.imageUrl}/>
                                                    </Link>
                                                </div>
                                            );
                                        }) :
                                        <p> Currently we don't have any similar movies to this one. Look for movies
                                            in <a className={"theListOfMoviesLink"} href={"/movie"}>the movie
                                                program</a>, or come back later.</p>}
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    );
}

export default movieDetailsWithScheduledMovies;