import pandas as pd
df = pd.read_csv('dataset.zip',compression='zip')



#preprocessing
from sklearn import model_selection
from sklearn import preprocessing
df.columns = ['duration', 'start_date', 'end_date', 'start_station_number','start_station', 'end_station_number', 'end_station', 'bike_number','member_type']
df.dropna(inplace=True)
X = df.drop(['member_type'],1)
le = preprocessing.LabelEncoder()
le.fit(df['member_type'])
Y = le.transform(df['member_type'])
print(Y)

X_train, X_test, Y_train, Y_test = model_selection.train_test_split(X,Y, test_size=0.2)

from sklearn.metrics import classification_report, accuracy_score, confusion_matrix
from sklearn.ensemble import RandomForestClassifier

clf = RandomForestClassifier()
clf.fit(X_train,Y_train)
predictions = clf.predict(X_test)

print(classification_report(Y_test,predictions))
print(accuracy_score(Y_test,predictions))
cm = confusion_matrix(Y_test,predictions)
print(cm)

import matplotlib.pyplot as plt
labels = le.inverse_transform(le.get_classes())
fig = plt.figure()
ax = fig.add_subplot(111)
cax = ax.matshow(cm)
fig.colorbar(cax)
plt.title("Classification using random forest")
ax.set_xticklabels(['']+labels)
ax.set_yticklabels(['']+labels)
plt.xlabel('Prediction')
plt.ylabel('True')
plt.show()

