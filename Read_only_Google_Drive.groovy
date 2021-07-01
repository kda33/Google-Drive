1. Open Google Drive and right-click any file that you wish to make a read-only file. Click the Share Link menu and copy the file link to the clipboard.

example 
https://docs.google.com/spreadsheets/d/12345_abcdef-123/edit?usp=sharing

2. Type script.new in the browser to open a new Google Apps Script project and copy-paste this snippet in the code editor.
___________________________________________________________
const makeFileReadyOnly = () => {
  const fileUrl = '<<FILE URL>>';
  const [fileId] = fileUrl.split('/').filter((e) => /[_-\w]{25,}/.test(e));
  UrlFetchApp.fetch(`https://www.googleapis.com/drive/v3/files/${fileId}`, {
    method: 'PATCH',
    contentType: 'application/json',
    headers: {
      Authorization: `Bearer ${ScriptApp.getOAuthToken()}`,
    },
    payload: JSON.stringify({
      contentRestrictions: [
        {
          readOnly: true,
          reason: 'Prevent accidental editing',
        },
      ],
    }),
  });
  // For requesting correct scope, do not delete
  // var file = DriveApp.getFileById().setName()
};

________________________________________________________
3. Replace the FILE URL in line #2 with the URL of the Drive file that you copied in the previous step.
4. Go to the Run menu, choose Run function > makeFileReadyOnly. Accept the permissions and your file will restricted from editing by anyone including yourself.
