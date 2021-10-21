import './App.css';

import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import React, {Component} from "react";

import MovieService from "../../repository/movieRepository";
import ScheduledMovieService from "../../repository/scheduledMovieRepository";
import TicketService from "../../repository/ticketRepository";

import Header from "../Header/header"
import Footer from "../Footer/footer"
import HomePage from "../Home/homePage"
import Movies from '../Movie/MovieList/movie';
import MoviesList from '../Movie/MovieList/movies'
import MovieAdd from "../Movie/MovieAdd/movieAdd";
import MovieDetailsWithScheduledMovies from "../Movie/MovieDetails/movieDetailsWithScheduledMovies"
import ScheduledMovie from "../ScheduledMovie/ScheduledMovieList/scheduledMovie"
import ScheduledMovieAdd from "../ScheduledMovie/ScheduledMovieAdd/scheduleMovieAdd";
import TicketReservationAdd from "../Ticket/TicketReservationAdd/ticketreservationAdd";
import TicketReservationDetails from "../Ticket/TicketDetails/ticketReservationDetails";
import ActiveReservation from "../Ticket/TicketDetails/activeReservation";
import ConfirmedReservations from "../Ticket/ConfirmedReservationsList/confirmedReservations"
import data from "bootstrap/js/src/dom/data";
import ReservationDetails from "../Ticket/TicketDetails/reservationDetails";
import MovieRate from "../Movie/MovieRate/movieRate";
import RescheduleMovie from "../ScheduledMovie/RescheduleMovie/rescheduleMovie";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies: [],
            scheduledMovies: [],
            selectedMovie: {},
            ticketReservation: {},
            scheduledMovie: {},
            activeReservation: {},
            scheduledMoviesForMovie: [],
            confirmedReservations: [],
            moviesWithPagination: [],
            moviesPage: {},
            similarMovies: []
        }
    }

    render() {
        return (
                <Router>
                    <div id="page-container">
                        <div id="content-wrap">
                            <Header/>
                        <main>
                        <div className="container">

                            <Route path={"/movie/fetchScheduledMoviesByMovieId/:id"} exact render={() =>
                                <MovieDetailsWithScheduledMovies selectedMovie={this.state.selectedMovie}
                                                                scheduledMovies={this.state.scheduledMoviesForMovie}
                                                                 similarMovies={this.state.moviesByGenre}
                                                                 onDetails={this.getMovie}
                                                                 onFindSimilarMovies={this.findSimilarMovies}
                                                                 onFetchScheduledMoviesByMovieId={this.fetchScheduledMoviesByMovieId}
                                                                 onBookTickets={this.getScheduledMovie}/>}/>

                            <Route path={"/movie/rateMovie/:id"} exact render={() =>
                                <MovieRate selectedMovie={this.state.selectedMovie}
                                           onRateMovie={this.rateMovie}/>}/>

                            <Route path={"/movie/add"} exact render={() =>
                                <MovieAdd onAddMovie={this.addMovie}/>}/>

                            <Route path={["/movie",""]}
                                   exact render={() =>
                                <Movies movies={this.state.movies}
                                        moviesPage={this.state.moviesPage}
                                        onDetails={this.getMovie}
                                        onRateMovie={this.getMovie}
                                        onFindSimilarMovies={this.findSimilarMovies}
                                        onFetchScheduledMoviesByMovieId={this.fetchScheduledMoviesByMovieId}
                                        onActiveReservation={this.getActiveReservation}
                                        onPageChange={this.loadMoviesWithPagination}
                                        onScheduleMovie={this.getMovie}/> } />

                            <Route path={["/movieList"]} exact render={() =>
                                <MoviesList movies={this.state.movies}
                                            onDetails={this.getMovie}
                                            onFetchScheduledMoviesByMovieId={this.fetchScheduledMoviesByMovieId}
                                            onScheduleMovie={this.getMovie}/>}/>


                            <Route path={"/ticket/getTicketReservation/:id"} exact render={() =>
                                <ReservationDetails activeReservation={this.state.ticketReservation}
                                                   onCancelReservation={this.cancelReservation}
                                                   onConfirmReservation={this.confirmReservation}/>}/>

                            <Route path={"/ticket/makeNewReservation/:id"} exact render={() =>
                                <TicketReservationAdd onTicketReservationAdd={this.addTicketReservation}
                                                      ticketReservation={this.state.ticketReservation}
                                                      scheduledMovie={this.state.scheduledMovie}/>}/>

                            <Route path={["/ticket/activeReservation"]}
                                   exact render={() =>
                                <ActiveReservation activeReservation={this.state.activeReservation}
                                                   onCancelReservation={this.cancelActiveReservation}
                                                   onConfirmReservation={this.confirmActiveReservation}/>}/>

                            <Route path={["/ticket/confirmedReservations"]}
                                   exact render={() =>
                                <ConfirmedReservations reservations={this.state.confirmedReservations}
                                                       onCancelConfirmedReservation={this.cancelConfirmedReservation}/> } />


                            <Route path={"/scheduledMovies/reschedule/:id"} exact render={() =>
                                <RescheduleMovie scheduledMovie={this.state.scheduledMovie}
                                           onReschedule={this.rescheduleMovie}/>}/>

                            <Route path={"/scheduledMovies/add/:id"} exact render={() =>
                                <ScheduledMovieAdd onAddScheduledMovie={this.addScheduledMovie}
                                                   selectedMovie={this.state.selectedMovie}/>}/>

                            <Route path={"/scheduledMovies/add"} exact render={() =>
                                <ScheduledMovieAdd onAddScheduledMovie={this.addScheduledMovie}/>}/>

                            <Route path={["/scheduledMovies"]}
                                   exact render={() =>
                                <ScheduledMovie scheduledMovies={this.state.scheduledMovies}
                                                movies={this.state.movies}
                                                onBookTickets={this.getScheduledMovie}
                                                onRescheduleMovie={this.getScheduledMovie}
                                                onCancelScheduledMove={this.cancelScheduledMovie}/> } />

                            {/*<Route path={"/ticket/makeReservation"} exact render={() =>*/}
                            {/*    <TicketReservationAdd onTicketReservationAdd={this.addTicketReservation}*/}
                            {/*                          movies={this.state.movies}/>}/>*/}



                            {/*<Route path={"/movie/scheduleMovie/:id"} exact render={() =>*/}
                            {/*    <ScheduleMovie onScheduleMovie={this.scheduleMovie}*/}
                            {/*    movie = {this.state.selectedMovie}/>}/>*/}


                            {/*<Route path={"/ticket/addTicketToReservation/:id"} exact render={() =>*/}
                            {/*    <AddTicketToReservation onAddTicketToReservation={this.addTicketToReservation}*/}
                            {/*                            ticketReservation={this.state.ticketReservation}/>}/>*/}

                            {/*<Route path={"/movie/findById/:id"} exact render={() =>*/}
                            {/*    <MovieDetails selectedMovie={this.state.selectedMovie}/>}/>*/}



                        </div>
                            <div>
                                <Route path={"/home"} exact render={() =>
                                    <HomePage movies={this.state.movies}
                                              onDetails={this.getMovie}
                                              onRateMovie={this.getMovie}
                                              onFetchScheduledMoviesByMovieId={this.fetchScheduledMoviesByMovieId}
                                              onActiveReservation={this.getActiveReservation}
                                              onPageChange={this.loadMoviesWithPagination}
                                              onScheduleMovie={this.getMovie}/>}/>
                            </div>
                        </main>
                    </div>
                    <Footer/>
                    </div>
                </Router>
        );
    }

    componentDidMount() {
        this.loadMovies();
        this.loadScheduledMovies();
        this.loadConfirmedReservations();
        this.loadMoviesWithPagination(0,3);
    }

    loadMovies = () => {
        MovieService.fetchMovies()
            .then((data) => {
                this.setState({
                    movies: data.data
                })
            });
    }

    findSimilarMovies = (genre) => {
        MovieService.fetchMoviesByGenre(genre)
            .then((data) => {
                this.setState({
                    moviesByGenre: data.data
                })
            });
    }

    loadMoviesWithPagination= (page,size) => {
        MovieService.fetchMoviesWithPagination(page,size)
            .then((data) => {
                this.setState({
                    moviesWithPagination: data.data.content,
                    moviesPage: data.data
                })
            });
    }

    loadScheduledMovies = () => {
        ScheduledMovieService.fetchScheduledMovies()
            .then((data) => {
                this.setState({
                    scheduledMovies: data.data
                })
            });
    }

    loadConfirmedReservations = () => {
        TicketService.getConfirmedReservations()
            .then((data) => {
                this.setState({
                    confirmedReservations: data.data
                })
            });
    }

    getMovie = (id) => {
       // console.log("vo getMovie"+id)
        MovieService.getMovie(id)
            .then((data) => {
                console.log("vo getMovie"+typeof (data.data))
                this.setState({
                    selectedMovie: data.data
                })
            })
    }


    getScheduledMovie = (id) => {
        // console.log("vo getMovie"+id)
        ScheduledMovieService.getScheduledMovie(id)
            .then((data) => {
                console.log("vo getScheduledMovie"+typeof (data.data))
                this.setState({
                    scheduledMovie: data.data
                })
            })
    }
    fetchScheduledMoviesByMovieId = (id) => {
        // console.log("vo getMovie"+id)
        ScheduledMovieService.fetchScheduledMoviesByMovieId(id)
            .then((data) => {
                console.log("vo fetchScheduledMoviesByMovieId"+typeof (data.data))
                this.setState({
                    scheduledMoviesForMovie: data.data
                })
            })
    }
    rateMovie = (id,rating) => {
        // console.log("vo getMovie"+id)
        MovieService.rateMovie(id,rating)
            .then((data) => {
                console.log("vo rate"+typeof (data.data))
                this.setState({
                    selectedMovie: data.data
                })
            })
            .then(() => {
                this.loadMovies();
            });
    }
    rescheduleMovie = (id,time,date) => {
        // console.log("vo getMovie"+id)
        ScheduledMovieService.rescheduleMovie(id,time,date)
            .then(() => {
                this.loadScheduledMovies();
            });
    }
    getTicketReservation = (id) => {
        // console.log("vo getMovie"+id)
        TicketService.getTicketReservation(id)
            .then((data) => {
                console.log("vo getMovie"+typeof (data.data))
                this.setState({
                    ticketReservation: data.data
                })
            })
    }

    getActiveReservation = () => {
        // console.log("vo getMovie"+id)
        TicketService.getActiveReservation()
            .then((data) => {
                console.log("vo getMovie"+typeof (data.data))
                this.setState({
                    activeReservation: data.data
                })
            })
    }

    cancelActiveReservation = () => {
        // console.log("vo getMovie"+id)
        TicketService.cancelActiveReservation()
            .then(() => {
                this.loadMovies();
            });
    }
    cancelConfirmedReservation = (id) => {
        // console.log("vo getMovie"+id)
        TicketService.cancelConfirmedReservation(id)
            .then(() => {
                this.loadConfirmedReservations();
            });
    }
    confirmReservation = (id) => {
        // console.log("vo getMovie"+id)
        TicketService.confirmReservation(id)
            .then(() => {
                this.loadConfirmedReservations();
            });
    }
    cancelReservation = (id) => {
        // console.log("vo getMovie"+id)
        TicketService.cancelReservation(id)
            .then(() => {
                this.loadConfirmedReservations();
            });
    }
    cancelScheduledMovie = (id) => {
        // console.log("vo getMovie"+id)
        ScheduledMovieService.cancelScheduledMovie(id)
            .then(() => {
                this.loadScheduledMovies();
            });
    }
    confirmActiveReservation = () => {
        // console.log("vo getMovie"+id)
        TicketService.confirmActiveReservation()
            .then(() => {
                this.loadMovies();
            });
    }
    addMovie = (name, movieLength, genre, publishDate, description,moviePoster,movieAdvertisementImage,trailerUrl) => {
        MovieService.addMovie(name, movieLength, genre, publishDate, description,moviePoster,movieAdvertisementImage,trailerUrl)
            .then(() => {
                this.loadMovies();
            });
    }

    addScheduledMovie = (sales, startDate, startTime, ticketPrice, movieId) => {
        ScheduledMovieService.addScheduledMovie(sales, startDate, startTime, ticketPrice, movieId)
            .then(() => {
                this.loadMovies();
                this.loadScheduledMovies();
            });
    }

    addTicketReservation = (currency,paymentMethod,tickets) => {
        TicketService.makeReservation(currency,paymentMethod,tickets)
            .then((data) => {
                console.log("vo getMovie"+typeof (data.data))
                this.setState({
                    ticketReservation: data.data
                })
            })
    }

    addTicketToReservation = (id,quantity,movie) => {
        TicketService.addTicketToReservation(id,quantity,movie)
            .then(() => {
                this.loadMovies();
            });
    }
    scheduleMovie = (id, startTime, endTime) => {
        MovieService.scheduleMovie(id, startTime,endTime)
            .then(() => {
                this.loadMovies();
            });
    }


}

export default App;
