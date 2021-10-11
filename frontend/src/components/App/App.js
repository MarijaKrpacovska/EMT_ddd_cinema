import './App.css';
import React, {Component} from "react";
import Movies from '../Movie/movie';
import MovieDetails from '../Movie/movieDetails'
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import MovieService from "../../repository/movieRepository";
import MovieAdd from "../Movie/movieAdd";
import ScheduleMovie from "../unused/scheduleMovie";
import TicketReservationAdd from "../Ticket/ticketreservationAdd";
import TicketService from "../../repository/ticketRepository";
import AddTicketToReservation from "../unused/addTicketToReservation";
import TicketReservationDetails from "../Ticket/ticketReservationDetails";
import ScheduledMovieService from "../../repository/scheduledMovieRepository";
import ScheduledMovie from "../ScheduledMovie/scheduledMovie"
import ActiveReservation from "../Ticket/activeReservation";
import ScheduledMovieAdd from "../ScheduledMovie/scheduleMovieAdd";
import Header from "../Header/header"

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies: [],
            scheduledMovies: [],
            selectedMovie: {},
            ticketReservation: {},
            scheduledMovie: {},
            activeReservation: {}
            //big changes
        }
    }

    render() {
        return (
                <Router>
                    <Header/>
                    <main>
                    <div className="container">
                        <Route path={"/movie/findById/:id"} exact render={() =>
                            <MovieDetails selectedMovie={this.state.selectedMovie}/>}/>

                        <Route path={"/movie/add"} exact render={() =>
                            <MovieAdd onAddMovie={this.addMovie}/>}/>

                        <Route path={["/movie",""]}
                               exact render={() =>
                            <Movies movies={this.state.movies}
                                    onDetails={this.getMovie}
                                    onActiveReservation={this.getActiveReservation}
                                    onScheduleMovie={this.getMovie}/> } />


                        <Route path={"/ticket/getTicketReservation/:id"} exact render={() =>
                            <TicketReservationDetails ticketReservation={this.state.ticketReservation}/>}/>

                        <Route path={"/ticket/makeNewReservation/:id"} exact render={() =>
                            <TicketReservationAdd onTicketReservationAdd={this.addTicketReservation}
                                                  scheduledMovie={this.state.scheduledMovie}/>}/>

                        <Route path={["/ticket/activeReservation"]}
                               exact render={() =>
                            <ActiveReservation activeReservation={this.state.activeReservation}
                                               onCancelReservation={this.cancelActiveReservation}
                                               onConfirmReservation={this.confirmActiveReservation}/>}/>


                        <Route path={"/scheduledMovies/add/:id"} exact render={() =>
                            <ScheduledMovieAdd onAddScheduledMovie={this.addScheduledMovie}
                                               selectedMovie={this.state.selectedMovie}/>}/>

                        <Route path={"/scheduledMovies/add"} exact render={() =>
                            <ScheduledMovieAdd onAddScheduledMovie={this.addScheduledMovie}/>}/>

                        <Route path={["/scheduledMovies"]}
                               exact render={() =>
                            <ScheduledMovie scheduledMovies={this.state.scheduledMovies}
                                            onBookTickets={this.getScheduledMovie}/> } />

                        {/*<Route path={"/ticket/makeReservation"} exact render={() =>*/}
                        {/*    <TicketReservationAdd onTicketReservationAdd={this.addTicketReservation}*/}
                        {/*                          movies={this.state.movies}/>}/>*/}



                        {/*<Route path={"/movie/scheduleMovie/:id"} exact render={() =>*/}
                        {/*    <ScheduleMovie onScheduleMovie={this.scheduleMovie}*/}
                        {/*    movie = {this.state.selectedMovie}/>}/>*/}


                        {/*<Route path={"/ticket/addTicketToReservation/:id"} exact render={() =>*/}
                        {/*    <AddTicketToReservation onAddTicketToReservation={this.addTicketToReservation}*/}
                        {/*                            ticketReservation={this.state.ticketReservation}/>}/>*/}

                    </div>
                    </main>
                </Router>
        );
    }

    componentDidMount() {
        this.loadMovies();
        this.loadScheduledMovies();
    }

    loadMovies = () => {
        MovieService.fetchMovies()
            .then((data) => {
                this.setState({
                    movies: data.data
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
    confirmActiveReservation = () => {
        // console.log("vo getMovie"+id)
        TicketService.confirmActiveReservation()
            .then(() => {
                this.loadMovies();
            });
    }
    addMovie = (name, movieLength, genre, publishDate, description,ticketPrice,url,scheduledMovies) => {
        MovieService.addMovie(name, movieLength, genre, publishDate, description,ticketPrice,url,scheduledMovies)
            .then(() => {
                this.loadMovies();
            });
    }
    addScheduledMovie = (sales, startTime, endTime, ticketPrice, movieId) => {
        ScheduledMovieService.addScheduledMovie(sales, startTime, endTime, ticketPrice, movieId)
            .then(() => {
                this.loadScheduledMovies();
            });
    }

    addTicketReservation = (reservationTime,currency,reservationStatus,paymentMethod,tickets) => {
        TicketService.makeReservation(reservationTime,currency,reservationStatus,paymentMethod,tickets)
            .then(() => {
                this.getActiveReservation();
            });
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
