#include <iostream>
#include <string>
using namespace std;

// Constructor:
class ShiftCipher {
private:
    int shiftAmount;

public:
    // Create 'shift' parameter to allow users to insert different types of shift amount values
    ShiftCipher(int shift) : shiftAmount(shift) {
        // Make sure the shift amount value is in the alphabet capacity which is between 0 and 25
        while (shiftAmount < 0 || shiftAmount > 25) {
            // Print an error message if the shift amount is out of the alphabet capacity
            cerr << "ERROR: Please enter a valid shiftAmount value in between 0 and 25 to use the letters in alphabet.\n";
            // Ask user to insert a new valid input after displaying the error message
            cout << "Please enter a valid shift amount value: ";
            cin >> shiftAmount;
        }
    }

    // Task: Encode function should take a string or a char array, and return another string or char array...
    // ... by shifting the letters in the alphabeth by the shiftAmount.
    string encode(const string & text) { // Work on the initial text form
        // Create the variables
        string encodedText = text;
        int n = encodedText.size();

        // Use 'for' loop to keep checking every character until you reach the size of the initial text
        for (int i = 0; i < n; i++) {
            // Check if the character in encoded text is lowercase. Set 'a' or 'A' according to this
            char initial = (encodedText[i] >= 'a' && encodedText[i] <= 'z') ? 'a' : 'A';
            // Determine if the character in "i" index is a letter or not
            if ((encodedText[i] >= 'a' && encodedText[i] <= 'z') || (encodedText[i] >= 'A' && encodedText[i] <= 'Z')) {
                // Calculate every character's encoded form in the initial text using ASCII representations
                // Substract the initial first and add the shift amount value after that. Finally, add the initial again
                encodedText[i] = ((encodedText[i] - initial + shiftAmount) % 26) + initial;
            }
        }
        // Return the encoded form of the initial text
        return encodedText;
    }

    // Task: Decode function should take a shift encoded string or char array and convert it back to its original form.
    string decode(const string & encodedText) { // Work on the encoded text form
        // Create the variables
        string decodedText = encodedText;
        int n = decodedText.size();

        // Use 'for' loop to keep checking every character until you reach the size of the encoded text
        for (int i = 0; i < n; i++) {
            // Check if the character in decoded text is lowercase. Set 'a' or 'A' according to this
            char initial = (decodedText[i] >= 'a' && decodedText[i] <= 'z') ? 'a' : 'A';
            // Determine if the character in "i" index is a letter or not
            if ((decodedText[i] >= 'a' && decodedText[i] <= 'z') || (decodedText[i] >= 'A' && decodedText[i] <= 'Z')) {
                // Calculate every character's decoded form in the encoded text using ASCII representations
                // Substract the initial and the shift amount values first. Finally, add the initial again
                decodedText[i] = ((decodedText[i] - initial - shiftAmount + 26) % 26) + initial;
            }
        }
        // Return the decoded form of the encoded text
        return decodedText;
    }
};

// Main method:
int main() {
    // Call the variables
    int shiftAmount;
    string initialText;

    cout << "\n";
    cout << "***QUESTIONS***" << endl;
    // Ask user to insert a shift amount value
    cout << "Please enter a shift amount value: ";
    cin >> shiftAmount;

    // Create an instance of the ShiftCipher class with constructor
    ShiftCipher shiftCipher(shiftAmount);

    // Ask user to write the text they want to shift
    cout << "Please enter the text you want to shift: ";
    cin >> initialText;

    // Use the text objects in the encode and decode methods
    string encodedText = shiftCipher.encode(initialText);
    string decodedText = shiftCipher.decode(encodedText);

    // Display the outputs one by one
    cout << "\n";
    cout << "***OUTPUTS***" << endl;
    cout << "Original Text: " << initialText << endl;
    cout << "Encoded Text by The Shift Amount Value " << shiftAmount << ": " << encodedText << endl;
    cout << "Decoded Text of The Encoded String or Char \"" << encodedText << "\": " << decodedText << endl;
    cout << "\n";
}
