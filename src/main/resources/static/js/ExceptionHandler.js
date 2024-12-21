export class ExceptionHandler {

    constructor(field) {
        this.field = field;
    }

    errors = new Map([
            ["0001", "Not found"],
            ["0002", "Session not found"],
            ["0003", "Not implemented"],
            ["0004", "Validation failed"],
            ["0005", "Low balance"]
    ]);

    handle(errorCode, error) {
        this.field.style.color = "red";
        this.field.textContent = this.errors.get(errorCode) + ": " + error;
    }
}