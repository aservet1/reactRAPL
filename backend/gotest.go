


func handleRequests() {
	myRouter := mux.NewRouter().StrictSlash(true)
	myRouter.HandleFunc("/api/test", homePage).Methods("GET")
	log.Fatal(http.ListenAndServe(":10000", myRouter))
}





func main() {
	handleRequests()
}



