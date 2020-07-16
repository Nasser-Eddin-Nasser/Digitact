<p align="center">
  <img src="images/frontend/logo.png" width="150px" alt="digitact logo">
</p>

# Features

Currently, most of the job applications at job fairs are handled "manually" on paper. Digitact is an app that brings this whole process into the digital era.

Digitact is built with simplicity and good UX in mind. Almost all features are self-explanatory.

This is a quick overview of the most important features.

## Supported platforms

Digitact can be compiled both for Android and iOS. The user interface just looks great on both operating systems. Also, it has been optimized for phone and (larger) tablet screens. Finally, Digitact also has support for Dark Mode. In the following, you can see an example that shows the same screen on Android phone, Android tablet, iOS phone and iOS tablet.

### Android Phone
<img src="images/frontend/platforms--key-competencies--android--phone.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/platforms--key-competencies--android--phone--dark-mode.png" width="250px" style="border: 1px solid #e0e0e0;">

### Android Tablet
<img src="images/frontend/platforms--key-competencies--android--tablet.png" width="500px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/platforms--key-competencies--android--tablet--dark-mode.png" width="500px" style="border: 1px solid #e0e0e0;">

### iOS Phone
<img src="images/frontend/platforms--key-competencies--ios--phone.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/platforms--key-competencies--ios--phone--dark-mode.png" width="250px" style="border: 1px solid #e0e0e0;">

### iOS Tablet
<img src="images/frontend/platforms--key-competencies--ios--tablet.png" width="500px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/platforms--key-competencies--ios--tablet--dark-mode.png" width="500px" style="border: 1px solid #e0e0e0;">

# Pages

## Landing page

<img src="images/frontend/landing-page.png" width="250px" style="border: 1px solid #e0e0e0;">

The landing page (also called "home page") is the very first screen the user sees when he opens the app. Here, right underneath the logo, a short headline related to the company is shown.

There is only one prominent button: "Apply now", which directly starts the job application process.


<img src="images/frontend/landing-page--languages.png" width="250px" style="border: 1px solid #e0e0e0;">

Digitact is available both in English and German. On the landing page, the user is able to choose his preferred language.

Above the "settings" icon, you can find another button that brings the user to the list of submitted applications. More about that later.

## Login page

<img src="images/frontend/login.png" width="250px" style="border: 1px solid #e0e0e0;">

When the app is started for the very first time, a login screen is displayed before the landing page is shown. This allows the user to register the device on the server. Later, when submitting data to the server, only registered devices are permitted to do so. This prevents unauthorized devices from using the API.

## The application form

### Privacy Policy prompt

<img src="images/frontend/privacy-policy-prompt.png" width="250px" style="border: 1px solid #e0e0e0;">

Once the user has pressed the "Apply now" button, he gets redirected to a page asking to read the Privacy Policy.

### "Welcome" Step

<img src="images/frontend/form--welcome--empty.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/form--welcome--filled.png" width="250px" style="border: 1px solid #e0e0e0;">

Once the user has accepted the Privacy Policy, he is redirected to the main application page.

In the top bar, the number of the current "Step" is shown. Since we are showing the "Welcome" Step here (which is the very first one), it says "Step 1 / 9". On the right, a hamburger-esque menu icon is shown. Clicking on it will reveal an overview of the Steps (more about that later).

Directly underneath the top bar, there is a progress bar. Once you have filled out all required data of a particular Step, it will instantly move to the right. If the progress bar has reached the right border, it means that all Steps have successfully been filled out and the form may be submitted. 

There is one special case: Some Steps are completely optional (like the Profile Picture Step). These Steps are automatically "checked" in the side menu and don't contribute to the progress bar.

### Side menu

<img src="images/frontend/form--side-menu.png" width="250px" style="border: 1px solid #e0e0e0;">

As already mentioned before, there is a menu on the right, which displays an overview of all the Steps. Once all required fields of a particular Step have been filled, a checkmark will appear for the respective menu item. This allows to quickly figure out where data is still missing. Clicking on a menu item allows to jump to this Step.

Also, the currently visible Step is highlighted in a different color. 

### "Contact Information" Step

<img src="images/frontend/form--contact-information--empty.png" width="250px" style="border: 1px solid #e0e0e0;">

In the second Step, the user shall enter contact information. This is especially useful to allow the HR agent to later contact the applicant.

### "Profile Picture" Step

<img src="images/frontend/form--profile-picture--empty.png" width="250px" style="border: 1px solid #e0e0e0;">

The third Step is optional. Here, the user may take a picture of himself (selfie).

### "Documents" Step

<img src="images/frontend/form--documents--empty.png" width="250px" style="border: 1px solid #e0e0e0;">

The fourth Step is also optional. Here, it is possible to take photos of documents (e.g. of CVs) in order to include them in the application. It is later also possible to view the images in a image viewer (with support for gestures like pinch-to-zoom and swiping).

### "Work Experience" Step

<img src="images/frontend/form--work-experience--empty.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/form--work-experience-modal--empty.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/form--work-experience--2-entries.png" width="250px" style="border: 1px solid #e0e0e0;">

The next Step asks for the work experience. This is the perfect place for the applicant to showcase all of the interesting jobs he had.

### "Education Information" Step

<img src="images/frontend/form--education-information--empty.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/form--education-information--2-entries.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/form--education-information-modal--empty.png" width="250px" style="border: 1px solid #e0e0e0;">

The next step allows the user to provide details regarding the schools/universities he has visited. Once the user clicks on the "plus" icon, an overlay is displayed asking for the details. Once all fields have been entered and "Save" is clicked, this item will be added to the list on already added items. If one entry shall be deleted, this can be done using a swipe gesture.

### "Industries and Positions" Step

<img src="images/frontend/form--industries-and-positions--empty.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/form--industries-and-positions-select-menu.png" width="250px" style="border: 1px solid #e0e0e0;">

Of course, it is also really important to know which industries and positions the applicant is interested in. This is handled in the "Industries and Positions" step.

### "Key Competencies" Step

<img src="images/frontend/form--key-competencies--filled.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/form--key-competencies-modal--rate.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/form--key-competencies-modal--search.png" width="250px" style="border: 1px solid #e0e0e0;">

It is important to find out about the strengths and weaknesses of the applicant. The Key Competencies Step allows the applicant to rate himself in various fields, such as language, business or programming skills.

### "Additional Information" Step

<img src="images/frontend/form--additional-information--empty.png" width="250px" style="border: 1px solid #e0e0e0;">

The last form step contains one text field where the applicant may enter any text he likes. This is especially important if he wants to mention something important that has not been asked for in the previous Steps.

### Submit

<img src="images/frontend/form--submit--disabled.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/form--submit--enabled.png" width="250px" style="border: 1px solid #e0e0e0;">

Finally, the applicant reaches the "Submit" page. If there are still some fields missing in one of the previous Steps, he cannot submit the form. Instead, he is informed that the Side Menu will show where he still needs to enter some data. (As explained before, the progress bar also shows this kind of information: All necessary data have been filled in once the bar reaches the right border.)

### "Done" page

<img src="images/frontend/form--done.png" width="250px" style="border: 1px solid #e0e0e0;">

Once the form has been submitted, the applicant is informed that he is done and there is nothing left he needs to do.

The job application is now stored locally on the device. The HR team member may now click on "Quit" in order to go back to the home screen. Then, a new job application can be entered. Or, he may click on "Continue". This will show a screen where a rating of the applicant is possible. It is also possible to perform this rating later.

## Applicant rating page

<img src="images/frontend/applicant-rating--score.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/applicant-rating--side-menu.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/applicant-rating--impression.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/applicant-rating--finalize--disabled.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/applicant-rating--finalize--enabled.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/applicant-rating--finalize-alert.png" width="250px" style="border: 1px solid #e0e0e0;">

The rating of the applicant is done using a similar UI as in the main job application form. All changes are saved automatically.

Once the rating has been completed, you can "finalize" the application. Only finalized applications can be sent to the server.

## List of submitted applications

<img src="images/frontend/landing-page.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/applications.png" width="250px" style="border: 1px solid #e0e0e0;">

<img src="images/frontend/applications-upload--in-progress.png" width="250px" style="border: 1px solid #e0e0e0;">

On the home page, there is a button on the right top that indicates the number of submitted applications. 

Once you click on it, you can see two categories of applications: First, the ones that have been finalized. These are the ones that can be sent to the server. Second, the not yet finished/finalized ones. You can now click on one of the not finished ones in order to change the ratings and to eventually finalize it.


